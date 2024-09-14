# Blog Application

Welcome to the **Blog Application**, a robust and efficient platform for creating, managing, and viewing blog posts. This project is built using **Java** and **Spring Boot**, featuring a relational SQL database for data storage. The application offers a clean and user-friendly interface for interacting with blog content.

## Features

- **Create Posts**: Add new blog posts with a title, content.
- **Edit and Update**: Modify existing blog entries.
- **Delete Posts**: Remove posts as needed.
- **View Blogs**: Display a list of all available blog posts and view them in detail.

## Technologies Used

- **Backend**: Built with `Java` and `Spring Boot` to handle business logic and server-side operations.
- **Database**: Uses `SQL` for structured data storage and management.
- **API**: RESTful services implemented with Spring's `REST API` capabilities.
- **Security**: Spring Security used for managing authentication and authorization.

## Getting Started

Follow the steps below to set up the project locally:

### Prerequisites

Ensure you have the following installed:

- **Java JDK** (version 11 or higher)
- **Maven** (for dependency management)
- **SQL Database** (e.g., MySQL, PostgreSQL)

### Installation

1. **Clone the repository:**

    ```bash
    git clone https://github.com/IbrahemKhaled74/Blog.git
    ```

2. **Navigate into the project directory:**

    ```bash
    cd Blog
    ```

3. **Configure database settings:**

    Update the `application.properties` file with your database credentials:

    ```
    spring.datasource.url=jdbc:mysql://localhost:3306/your_db_name
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update
    ```

4. **Build the project:**

    ```bash
    mvn clean install
    ```

5. **Run the application:**

    ```bash
    mvn spring-boot:run
    ```

6. Open `http://localhost:8080` in your browser to access the application.

## API Endpoints


## 1. **Auth**

### POST `/blog/auth/login` or `/blog/auth/signin`
- **Description**: Authenticate a user and return a JWT token.
- **Request Body**: `LoginDto` containing username and password.
- **Response**: JWT token in `JWTResponse`.

### POST `/blog/auth/register` or `/blog/auth/signup`
- **Description**: Register a new user.
- **Request Body**: `RegisterDto` containing user details.
- **Response**: Success message with HTTP status `201 Created`.

  
## 2. **Post**

### POST `/blog/posts`
- **Description**: Create a new Post.
- **Request Body**: `Post` containing post information.
- **Authorization**: Admin only.
- **Response**: Created `Post` with HTTP status `201 Created`.

 ### GET `/blog/posts`
- **Description**: Fetch all categories.
- **Response**: List of `Posts`.

### GET `/blog/posts/{id}`
- **Description**: Fetch a specific post by ID.
- **Path Variable**: `id` - The ID of the post.
- **Response**: `post`.
  
### GET `/blog/posts/category/{id}`
- **Description**: Fetch a specific post by category.
- **Path Variable**: `id` - The category of the post.
- **Response**: `post`.
  
### PUT `/blog/posts/{id}`
- **Description**: Update an existing Post.
- **Path Variable**: `id` - The ID of the Post to be updated.
- **Request Body**: `Post` containing updated Post information.
- **Authorization**: Admin only.
- **Response**: Updated `Post`.

### DELETE `/blog/posts/{id}`
- **Description**: Delete a Post by its ID.
- **Path Variable**: `id` - The ID of the Post to be deleted.
- **Authorization**: Admin only.
- **Response**: Success message.


## 3. **Category**

### POST `/blog/category`
- **Description**: Create a new category.
- **Request Body**: `Category` containing category information.
- **Authorization**: Admin only.
- **Response**: Created `Category` with HTTP status `201 Created`.

### GET `/blog/category`
- **Description**: Fetch all categories.
- **Response**: List of `Categories`.

### GET `/blog/category/{id}`
- **Description**: Fetch a specific category by ID.
- **Path Variable**: `id` - The ID of the category.
- **Response**: `Category`.

### PUT `/blog/category/{id}`
- **Description**: Update an existing category.
- **Path Variable**: `id` - The ID of the category to be updated.
- **Request Body**: `Category` containing updated category information.
- **Authorization**: Admin only.
- **Response**: Updated `Category`.

### DELETE `/blog/category/{id}`
- **Description**: Delete a category by its ID.
- **Path Variable**: `id` - The ID of the category to be deleted.
- **Authorization**: Admin only.
- **Response**: Success message.

## 4. **Comment**

### POST `/blog/posts/{postId}/comments`
- **Description**: Create a new comment on a blog post.
- **Path Variable**: `postId` - The ID of the blog post.
- **Request Body**: `Comment` containing comment details.
- **Response**: Created `Comment` with HTTP status `201 Created`.

### GET `/blog/posts/{postId}/comments`
- **Description**: Fetch all comments for a specific post.
- **Path Variable**: `postId` - The ID of the blog post.
- **Response**: List of `Comment`.

### GET `/blog/posts/{postId}/comments/{commentId}`
- **Description**: Fetch a specific comment by its ID.
- **Path Variable**: `postId` - The ID of the blog post.
- **Path Variable**: `commentId` - The ID of the comment.
- **Response**: `Comment`.

### PUT `/blog/posts/{postId}/comments/{commentId}`
- **Description**: Update a specific comment by its ID.
- **Path Variable**: `postId` - The ID of the blog post.
- **Path Variable**: `commentId` - The ID of the comment to be updated.
- **Request Body**: `Comment` containing updated comment details.
- **Response**: Updated `Comment`.

### DELETE `/blog/posts/{postId}/comments/{commentId}`
- **Description**: Delete a specific comment by its ID.
- **Path Variable**: `postId` - The ID of the blog post.
- **Path Variable**: `commentId` - The ID of the comment to be deleted.
- **Response**: Success message.

---

This API provides a clean interface for managing users, categories, and comments for blog posts. The application includes authentication using JWT, and role-based authorization is enforced for admin-level actions.

## Contributing

Contributions are welcome! To contribute:

1. Fork the repository.
2. Create a new feature branch: `git checkout -b feature-branch`.
3. Commit your changes: `git commit -m "Add new feature"`.
4. Push the branch: `git push origin feature-branch`.
5. Open a pull request for review.

