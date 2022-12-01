<script>
	export let currentRoute;
	export let backRoute;
	export let currentCategory;
	export let taskToEdit = {
		categoryId: currentCategory.id,
		description: "",
		flag: "",
		name: "",
		points: 0,
	};

	import { editTask } from "../../common/api.js";

	let error = {
		name: "",
		description: "",
		points: "",
		flag: "",
	};

	let oldTaskName = taskToEdit.name;

	const editTaskHandler = async () => {
		let isValid = true;
		if (taskToEdit.name === "") {
			error.name = "Name is required";
			isValid = false;
		} else {
			error.name = "";
		}
		if (taskToEdit.description === "") {
			error.description = "Description is required";
			isValid = false;
		} else {
			error.description = "";
		}
		if (taskToEdit.points <= 0) {
			error.points = "Number of points must be greater than 0";
			isValid = false;
		} else {
			error.points = "";
		}
		if (taskToEdit.flag === "") {
			error.flag = "Flag is required";
			isValid = false;
		} else {
			error.flag = "";
		}
		if (!isValid) {
			return;
		}
		const { success } = await editTask(taskToEdit);
		if (success) {
			backRoute = currentRoute;
			currentRoute = "/category";
		} else {
			alert("Error occured, is tasks server running?");
		}
	};
</script>

<div>
	<p>Inside of: <b>{currentCategory.name}</b></p>
	<p>Editing <b>{oldTaskName}</b></p>
	<p>Name of the task:</p>
	<span class="error">{error.name}</span>
	<input type="text" bind:value={taskToEdit.name} />

	<p>Description of the task:</p>
	<span class="error">{error.description}</span>
	<input type="text" bind:value={taskToEdit.description} />

	<p>Points for the task:</p>
	<span class="error">{error.points}</span>
	<input type="number" bind:value={taskToEdit.points} min="0" />

	<p>Flag for the task:</p>
	<span class="error">{error.flag}</span>
	<input type="text" bind:value={taskToEdit.flag} />

	<br />
	<br />
	<button on:click={editTaskHandler}>Submit</button>
</div>

<style>
	.error {
		color: red;
		font-weight: bold;  
	}
</style>
