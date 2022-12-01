<script>
	export let currentRoute;
	export let backRoute;
	export let currentCategory;
	import { createTask } from "../../common/api.js";

	let task = {
		categoryId: currentCategory.id,
		description: "",
		flag: "",
		name: "",
		points: 0,
	};

	let error = {
		name: "",
		description: "",
		points: "",
		flag: "",
	};

	const createTaskHandler = async () => {
		let isValid = true;
		if (task.name === "") {
			error.name = "Name is required";
			isValid = false;
		} else {
			error.name = "";
		}
		if (task.description === "") {
			error.description = "Description is required";
			isValid = false;
		} else {
			error.description = "";
		}
		if (task.points <= 0) {
			error.points = "Number of points must be greater than 0";
			isValid = false;
		} else {
			error.points = "";
		}
		if (task.flag === "") {
			error.flag = "Flag is required";
			isValid = false;
		} else {
			error.flag = "";
		}
		if (!isValid) {
			return;
		}
		const { success } = await createTask(task);
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
	<p>Name of the task:</p>
	<span class="error">{error.name}</span>
	<input type="text" bind:value={task.name} />

	<p>Description of the task:</p>
	<span class="error">{error.description}</span>
	<input type="text" bind:value={task.description} />

	<p>Points for the task:</p>
	<span class="error">{error.points}</span>
	<input type="number" bind:value={task.points} min="0" />

	<p>Flag for the task:</p>
	<span class="error">{error.flag}</span>
	<input type="text" bind:value={task.flag} />

	<br />
	<br />
	<button on:click={createTaskHandler}>Create</button>
</div>

<style>
	.error {
		color: red;
		font-weight: bold;
	}
</style>
