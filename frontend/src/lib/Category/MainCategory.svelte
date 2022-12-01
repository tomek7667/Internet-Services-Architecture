<script>
	export let currentRoute;
	export let currentCategory;
	export let currentTask;
	export let backRoute;
	export let taskToEdit;

	import { onMount } from "svelte";
	import { getTasks } from "../../common/api";
	import Task from "../Task.svelte";
	import CreateTask from "../Task/CreateTask.svelte";

	let tasks = [];

	onMount(async () => {
		let localTasks = await getTasks(currentCategory.id);
		if (localTasks.success) {
			tasks = localTasks.data;
		} else {
			alert("Error occured, is tasks server running?");
		}
	});

	const routeToCreateTask = () => {
		backRoute = currentRoute;
		currentRoute = "/create-task";
	};
</script>

<div>
	<p>Inside of: <b>{currentCategory.name}</b></p>
	<div class="categoryDescription">{currentCategory.description}</div>
	{#each tasks as task}
		<Task
			bind:task
			bind:currentRoute
			bind:currentTask
			bind:tasks
			bind:backRoute
			bind:taskToEdit
		/>
	{/each}
	<br />
	<button on:click={routeToCreateTask}>Create Task</button>
</div>

<style>
	.categoryDescription {
		margin: 20px;
	}
</style>
