# User CRUD Application

A modern, responsive User Management System built with Spring Boot, featuring a beautiful and unique CSS design.

## 🚀 Features

- **Complete CRUD Operations**: Create, Read, Update, and Delete users
- **Modern UI/UX**: Beautiful gradient design with glassmorphism effects
- **Responsive Design**: Works perfectly on desktop, tablet, and mobile devices
- **Real-time Search**: Search users by name with instant results
- **Form Validation**: Client-side and server-side validation
- **Auto-save Draft**: Form data is automatically saved to localStorage
- **Interactive Elements**: Hover effects, animations, and loading spinners
- **REST API**: Full REST API endpoints for programmatic access
- **Database Integration**: H2 in-memory database with sample data

## 🎨 Design Highlights

- **Unique Color Scheme**: Custom gradient combinations with CSS variables
- **Glassmorphism Effects**: Translucent cards with backdrop blur
- **Smooth Animations**: Fade-in, slide-in, and hover animations
- **Modern Typography**: Clean, readable fonts with proper hierarchy
- **Interactive Components**: Buttons with hover effects and loading states
- **Responsive Grid**: Flexible layout that adapts to any screen size

## 🛠️ Technology Stack

- **Backend**: Spring Boot 3.4.5
- **Database**: H2 (in-memory) / MySQL (production)
- **Frontend**: Thymeleaf templates
- **Styling**: Custom CSS with modern design patterns
- **Icons**: Font Awesome 6.0
- **Java Version**: 17

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Modern web browser

## 🚀 Quick Start

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd user-crud
   ```

2. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```
   or
   ```bash
   mvn spring-boot:run
   ```

3. **Access the application**
   - Main application: http://localhost:8080
   - H2 Database Console: http://localhost:8080/h2-console
     - JDBC URL: `jdbc:h2:mem:testdb`
     - Username: `sa`
     - Password: `password`

## 📱 Application Features

### User Management
- **View All Users**: See a complete list of users with pagination
- **Add New User**: Create new user accounts with validation
- **Edit User**: Update existing user information
- **Delete User**: Remove users with confirmation dialog
- **Search Users**: Find users by name with real-time results

### User Information
- First Name (required)
- Last Name (required)
- Email Address (required, unique)
- Phone Number (optional)
- Address (optional)
- Created/Updated timestamps

### UI Components
- **Header**: Beautiful gradient title with icons
- **Search Bar**: Real-time search functionality
- **Data Table**: Responsive table with hover effects
- **Action Buttons**: Color-coded buttons with icons
- **Form Validation**: Real-time field validation
- **Alert Messages**: Success/error notifications
- **Statistics Cards**: User count and system health

## 🎯 API Endpoints

### Web Interface
- `GET /` - Home page with user list
- `GET /add` - Add user form
- `GET /edit/{id}` - Edit user form
- `POST /users` - Create new user
- `POST /users/{id}` - Update user
- `GET /delete/{id}` - Delete user
- `GET /search` - Search users

### REST API
- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `POST /api/users` - Create user
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

## 🎨 CSS Features

### Design System
- **CSS Variables**: Consistent color scheme and spacing
- **Gradient Backgrounds**: Beautiful gradient combinations
- **Glassmorphism**: Translucent cards with backdrop blur
- **Responsive Grid**: CSS Grid and Flexbox layouts
- **Custom Animations**: Smooth transitions and keyframes

### Components
- **Cards**: Glassmorphism effect with hover animations
- **Buttons**: Gradient backgrounds with hover effects
- **Forms**: Modern input styling with focus states
- **Tables**: Responsive tables with hover effects
- **Alerts**: Color-coded notification system

### Responsive Design
- **Mobile First**: Optimized for mobile devices
- **Breakpoints**: Responsive design at multiple screen sizes
- **Touch Friendly**: Large touch targets for mobile
- **Flexible Layout**: Adapts to any screen size

## 🔧 Configuration

### Database Configuration
The application uses H2 in-memory database by default. To use MySQL:

1. Update `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/user_crud
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
   ```

2. Comment out H2 dependency in `pom.xml`

### Customization
- **Colors**: Modify CSS variables in `style.css`
- **Layout**: Adjust grid and flexbox properties
- **Animations**: Customize keyframe animations
- **Icons**: Replace Font Awesome icons

## 📊 Sample Data

The application comes with 5 sample users:
- John Doe (john.doe@example.com)
- Jane Smith (jane.smith@example.com)
- Mike Johnson (mike.johnson@example.com)
- Sarah Williams (sarah.williams@example.com)
- David Brown (david.brown@example.com)

## 🐛 Troubleshooting

### Common Issues
1. **Port 8080 in use**: Change `server.port` in `application.properties`
2. **Database connection**: Check database credentials and connection
3. **Static resources not loading**: Clear browser cache or restart application

### Logs
- Application logs are available in the console
- SQL queries are logged for debugging
- Error messages are displayed in the UI

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License.

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- Font Awesome for the beautiful icons
- The open-source community for inspiration

---

**Enjoy managing your users with style! 🎉** 