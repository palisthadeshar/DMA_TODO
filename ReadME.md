A to-do app is created using MVVM, Fragment, RecyclerView, and Room Database so that the app is scalable and maintainable. By separating the application's logic from the UI, the Model-View-ViewModel (MVVM) architecture makes it simpler to maintain and test the code. The UI of the program is broken up into reusable parts that can be added to or deleted from the screen as needed. When a list of things, such as tasks or notes, is displayed, the user can interact with them using the RecyclerView. The information is kept locally on the device via the Room Database, making it accessible even when the app is not running.

**Work flow of the app**
1. The user interacts with the UI, such as adding or deleting a task or note.
2. The View layer sends the user's input to the ViewModel layer.
3. The ViewModel layer updates the Model layer by adding or deleting the task or note from the Room Database.
4. The Model layer updates the ViewModel layer with the new data.
5. The ViewModel layer updates the View layer with the new data using LiveData, which automatically updates the UI

**Installation Guideline**
1. Download Zip
2. Download dependency
3. Select device
4. Run the app

**Features Of App**
1) A User Friendly UI Interface
2) Create task
3) Update task
4) Delete a task or all tasks
5) Error validation when input fields are null

![gif](todoapp.gif)


