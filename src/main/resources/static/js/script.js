// CREATE STUDENT

const studentForm = document.getElementById("studentForm");
// Find the HTML element whose ID is studentForm.

if (studentForm) {

    studentForm.addEventListener("submit", function (event) {
        // Prevent the default form submission behavior
        event.preventDefault();

        const name =
            document.getElementById("name").value.trim();

        const email =
            document.getElementById("email").value.trim();

        const department =
            document.getElementById("department").value.trim();

        const message =
            document.getElementById("message");


        // Clear previous message
        message.innerText = "";


        // Name validation
        if (name === "") {

            message.innerText =
                "Student name is required.";

            return;
        }


        if (name.length < 3) {

            message.innerText =
                "Student name must contain at least 3 characters.";

            return;
        }


        // Email validation
        if (email === "") {

            message.innerText =
                "Email address is required.";

            return;
        }


        if (!email.includes("@")) {

            message.innerText =
                "Please enter a valid email address.";

            return;
        }


        // Department validation
        if (department === "") {

            message.innerText =
                "Department is required.";

            return;
        }


        // Create student object
        const student = {

            name: name,

            email: email,

            department: department
        };


        // Send request to Spring Boot
        fetch("/students", {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(student)

        })

        .then(response => response.json())

        .then(data => {

            message.innerText =
                "Student created successfully.";

            studentForm.reset();

        })

        .catch(error => {

            message.innerText =
                "Error creating student.";

            console.error(error);

        });

    });
}


// ========================================
// READ ALL STUDENTS
// ========================================

const studentTableBody =
    document.getElementById("studentTableBody");

if (studentTableBody) {

    fetch("/students")

        .then(response => {

            if (!response.ok) {
                throw new Error("Failed to load students");
            }

            return response.json();

        })

        .then(students => {

            studentTableBody.innerHTML = "";

            students.forEach(student => {

                const row = document.createElement("tr");

                row.innerHTML = `

                    <td>${student.id}</td>

                    <td>${student.name}</td>

                    <td>${student.email}</td>

                    <td>${student.department}</td>

                    <td>

                        <button
                            class="button"
                            onclick="editStudent(${student.id})">
                            Edit
                        </button>

                        <button
                            class="button"
                            onclick="deleteStudent(${student.id})">
                            Delete
                        </button>

                    </td>

                `;

                studentTableBody.appendChild(row);

            });

        })

        .catch(error => {

            console.error(
                "Error loading students:",
                error
            );

        });

}



// ========================================
// DELETE STUDENT
// ========================================

function deleteStudent(id) {

    const confirmed =
        confirm("Are you sure you want to delete this student?");

    if (!confirmed) {
        return;
    }

    fetch(`/students/${id}`, {

        method: "DELETE"

    })

    .then(response => {

        if (!response.ok) {
            throw new Error("Failed to delete student");
        }

        return response.text();

    })

    .then(message => {

        alert(message);

        location.reload();

    })

    .catch(error => {

        console.error(
            "Error deleting student:",
            error
        );

        alert("Error deleting student");

    });

}



// ========================================
// EDIT STUDENT
// ========================================

function editStudent(id) {

    const name =
        prompt("Enter new student name:");

    if (name === null) {
        return;
    }


    const email =
        prompt("Enter new email:");

    if (email === null) {
        return;
    }


    const department =
        prompt("Enter new department:");

    if (department === null) {
        return;
    }


    if (
        name.trim() === "" ||
        email.trim() === "" ||
        department.trim() === ""
    ) {

        alert("All fields are required");

        return;
    }


    const student = {

        name: name.trim(),

        email: email.trim(),

        department: department.trim()

    };


    fetch(`/students/${id}`, {

        method: "PUT",

        headers: {

            "Content-Type": "application/json"

        },

        body: JSON.stringify(student)

    })

    .then(response => {

        if (!response.ok) {

            throw new Error(
                `Update failed. Status: ${response.status}`
            );

        }

        return response.json();

    })

    .then(updatedStudent => {

        alert("Student updated successfully!");

        location.reload();

    })

    .catch(error => {

        console.error(
            "Error updating student:",
            error
        );

        alert("Error updating student. Check the console.");

    });

}