<script>
    export let currentRoute;
    export let currentTask;
    export let tasks;
    export let task;
    export let backRoute;

    import {getTask, deleteTask} from '../common/api.js';

    const getTaskHandler = async () => {
        const result = await getTask(task.id);
        if (result.success) {
            backRoute = currentRoute;
            currentRoute = "/task";
            currentTask = result.data;
        } else {
            alert("Error occured, is tasks server running?");
        }
    }

    const deleteTaskHandler = async () => {
        const result = await deleteTask(task.id);
        if (result.success) {
            tasks = tasks.filter(t => t.id !== task.id);
        } else {
            alert("Error occured, is tasks server running?");
        }
    }
</script>

<div>
    <p>Task: <b>{task.name}</b></p>
    <button on:click={getTaskHandler}>Enter</button>
    <button on:click={deleteTaskHandler}>Delete</button>
</div>