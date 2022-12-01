<script>
	import { getCategories } from "./common/api.js";
	import { onMount } from "svelte";
	import Category from "./lib/Category.svelte";
	import MainCategory from "./lib/Category/MainCategory.svelte";
	import CreateCategory from "./lib/Category/CreateCategory.svelte";
	import MainTask from "./lib/Task/MainTask.svelte";
	import CreateTask from "./lib/Task/CreateTask.svelte";
	import EditTask from "./lib/Task/EditTask.svelte";
	import EditCategory from "./lib/Category/EditCategory.svelte";

	let categories = [];
	let currentRoute = "/";
	let backRoute = "/";
	let currentCategory = {};
	let currentTask = {};
	let taskToEdit = {
		name: "",
		description: "",
		categoryId: "",
		flag: "",
		points: 0,
	};
	let categoryToEdit = {
		name: "",
		description: "",
	};

	onMount(async () => {
		let localCategories = await getCategories();
		if (localCategories.success) {
			categories = localCategories.data;
		} else {
			alert("Error occured, is categories server running?");
		}
	});
</script>

<main>
	<h1>CTF Task - ISA - 184571</h1>
	{#if backRoute !== "/"}
		<button
			on:click={() => {
				currentRoute = backRoute;
				backRoute = "/";
			}}>Go back</button
		>
	{/if}
	{#if currentRoute === "/category"}
		<MainCategory
			bind:currentRoute
			bind:currentCategory
			bind:currentTask
			bind:backRoute
			bind:taskToEdit
		/>
	{:else if currentRoute === "/task"}
		<MainTask bind:currentTask />
	{:else if currentRoute === "/create-category"}
		<CreateCategory bind:currentRoute bind:backRoute bind:categories />
	{:else if currentRoute === "/edit-category"}
		<EditCategory
			bind:currentRoute
			bind:backRoute
			bind:categories
			bind:categoryToEdit
		/>
	{:else if currentRoute === "/create-task"}
		<CreateTask bind:currentRoute bind:backRoute bind:currentCategory />
	{:else if currentRoute === "/edit-task"}
		<EditTask
			bind:currentRoute
			bind:backRoute
			bind:currentCategory
			bind:taskToEdit
		/>
	{:else}
		{#each categories as category}
			<Category
				{category}
				bind:categories
				bind:currentRoute
				bind:currentCategory
				bind:backRoute
				bind:categoryToEdit
			/>
		{/each}
		<br />
		<button
			on:click={() => {
				backRoute = currentRoute;
				currentRoute = "/create-category";
			}}>Create Category</button
		>
	{/if}
</main>
<footer>
	<span>Created by: Tomasz Dąbrowski</span>
	{#if currentRoute !== "/"}
		<button
			on:click={() => {
				backRoute = currentRoute;
				currentRoute = "/";
			}}>Go Home</button
		>
	{/if}
</footer>

<style>
	footer {
		margin-top: 20px;
	}
</style>
