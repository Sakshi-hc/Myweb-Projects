let tasksCache = [];

function toLocalDateTimeString(date) {
  if (!(date instanceof Date)) date = new Date(date);
  const pad = (n) => n.toString().padStart(2, '0');
  return date.getFullYear() + '-' +
         pad(date.getMonth() + 1) + '-' +
         pad(date.getDate()) + 'T' +
         pad(date.getHours()) + ':' +
         pad(date.getMinutes()) + ':' +
         pad(date.getSeconds());
}

async function getTasks() {
  try {
    const res = await fetch("http://localhost:8080/api/tasks");
    if (!res.ok) {
      throw new Error(`HTTP error! status: ${res.status}`);
    }
    const data = await res.json();
    tasksCache = data;
    renderTasks(data);
  } catch (error) {
    console.error('Error fetching tasks:', error);
  }
}

async function addTask(task) {
  try {
    const res = await fetch("http://localhost:8080/api/tasks", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(task)
    });
    if (!res.ok) {
      throw new Error(`HTTP error! status: ${res.status}`);
    }
    await getTasks();
  } catch (error) {
    console.error('Error adding task:', error);
  }
}

async function updateTask(id, task) {
  try {
    const res = await fetch(`http://localhost:8080/api/tasks/${id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(task)
    });
    if (!res.ok) {
      throw new Error(`HTTP error! status: ${res.status}`);
    }
    await getTasks();
  } catch (error) {
    console.error('Error updating task:', error);
  }
}

async function deleteTask(id) {
  try {
    const res = await fetch(`http://localhost:8080/api/tasks/${id}`, { method: "DELETE" });
    if (!res.ok) {
      throw new Error(`HTTP error! status: ${res.status}`);
    }
    await getTasks();
  } catch (error) {
    console.error('Error deleting task:', error);
  }
}

function renderTasks(tasks) {
  const list = document.getElementById("tasks");
  list.innerHTML = "";
  
  const searchTerm = document.getElementById("search").value.toLowerCase();
  const filteredTasks = tasks.filter(task => 
    task.title.toLowerCase().includes(searchTerm) ||
    (task.description && task.description.toLowerCase().includes(searchTerm))
  );
  
  filteredTasks.forEach(task => {
    const li = document.createElement("li");
    li.className = "task-item";
    
    if (task.completed) {
      li.classList.add("completed");
    }

    li.innerHTML = `
      <div class="task-content">
        <input type="checkbox" ${task.completed ? "checked" : ""} onchange="toggleComplete(${task.id}, this.checked)" />
        <span class="task-title">${task.title}</span>
        ${task.description ? `<span class="task-description">${task.description}</span>` : ''}
        ${task.dueDate ? `<span class="task-due">Due: ${new Date(task.dueDate).toLocaleDateString()}</span>` : ''}
        <span class="task-priority priority-${task.priority}">${getPriorityLabel(task.priority)}</span>
      </div>
      <div class="task-actions">
        <button class="btn-edit" onclick="editTask(${task.id})" title="Edit">
          <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path>
            <path d="m18.5 2.5 3 3L12 15l-4 1 1-4 9.5-9.5z"></path>
          </svg>
        </button>
        <button class="btn-delete" onclick="deleteTask(${task.id})" title="Delete">
          <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <polyline points="3,6 5,6 21,6"></polyline>
            <path d="m19,6v14a2,2 0 0,1 -2,2H7a2,2 0 0,1 -2,-2V6m3,0V4a2,2 0 0,1 2,-2h4a2,2 0 0,1 2,2v2"></path>
          </svg>
        </button>
      </div>
    `;

    list.appendChild(li);
  });
}

function getPriorityLabel(priority) {
  switch(priority) {
    case 1: return "High";
    case 2: return "Normal";
    case 3: return "Low";
    default: return "Normal";
  }
}

function editTask(id) {
  const task = tasksCache.find(t => t.id === id);
  if (!task) return;
  
  // Populate form with task data
  document.getElementById("title").value = task.title;
  document.getElementById("description").value = task.description || "";
  document.getElementById("priority").value = task.priority;
  if (task.dueDate) {
    document.getElementById("due").value = toLocalDateTimeString(task.dueDate).slice(0, 16);
  }
  document.getElementById("edit-id").value = task.id;
  document.getElementById("submit-btn").textContent = "Update";
  document.getElementById("cancel-edit").style.display = "inline-block";
  
  // Scroll to form
  document.getElementById("task-form").scrollIntoView({ behavior: "smooth" });
}

function cancelEdit() {
  document.getElementById("task-form").reset();
  document.getElementById("edit-id").value = "";
  document.getElementById("submit-btn").textContent = "Add";
  document.getElementById("cancel-edit").style.display = "none";
}

async function clearCompleted() {
  const completedTasks = tasksCache.filter(task => task.completed);
  for (const task of completedTasks) {
    await deleteTask(task.id);
  }
}

document.getElementById("task-form").addEventListener("submit", async (e) => {
  e.preventDefault();
  const title = document.getElementById("title").value.trim();
  const description = document.getElementById("description").value.trim();
  const priority = parseInt(document.getElementById("priority").value);
  const dueVal = document.getElementById("due").value;
  const dueDate = dueVal ? toLocalDateTimeString(new Date(dueVal)) : null;
  const editId = document.getElementById("edit-id").value;

  if (editId) {
    // Update existing task
    await updateTask(parseInt(editId), {
      title,
      description,
      priority,
      dueDate,
      completed: false
    });
    cancelEdit();
  } else {
    // Create new task
    await addTask({ title, description, priority, dueDate, completed: false });
    e.target.reset();
  }
});

document.getElementById("cancel-edit").addEventListener("click", cancelEdit);

document.getElementById("clear-completed").addEventListener("click", clearCompleted);

document.getElementById("search").addEventListener("input", () => {
  renderTasks(tasksCache);
});

async function toggleComplete(id, completed) {
  const task = tasksCache.find(t => t.id === id);
  if (!task) return;
  
  // Handle dueDate properly - it might be a string from server or need conversion
  let dueDate = null;
  if (task.dueDate) {
    if (typeof task.dueDate === 'string') {
      dueDate = task.dueDate; // Already in correct format
    } else {
      dueDate = toLocalDateTimeString(task.dueDate);
    }
  }
  
  await updateTask(id, {
    title: task.title,
    description: task.description || "",
    completed,
    priority: task.priority,
    dueDate: dueDate
  });
}

getTasks();
