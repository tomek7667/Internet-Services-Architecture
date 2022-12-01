<script>
	export let currentRoute;
	export let backRoute;
	export let categories;
	import { createCategory, getCategories } from "../../common/api.js";

	let category = {
		name: "",
		description: "",
	};

	let error = {
		name: "",
		description: "",
	};

	const createCategoryHandler = async () => {
		let isValid = true;
		if (category.name === "") {
			error.name = "Name is required";
			isValid = false;
		} else {
			error.name = "";
		}
		if (category.description === "") {
			error.description = "Description is required";
			isValid = false;
		} else {
			error.description = "";
		}
		if (!isValid) {
			return;
		}
		const { success } = await createCategory(category);
		if (success) {
			let localCategories = await getCategories();
			if (localCategories.success) {
				categories = localCategories.data;
			} else {
				alert("Error occured, is categories server running?");
			}
			backRoute = currentRoute;
			currentRoute = "/";
		} else {
			alert("Error occured, is categories server running?");
		}
	};
</script>

<div>
	<h3>Create Category</h3>
	<p>Name of the category:</p>
	<span class="error">{error.name}</span>
	<input type="text" bind:value={category.name} />
	<p>Description of the category:</p>
	<span class="error">{error.description}</span>
	<input type="text" bind:value={category.description} />
	<br />
	<br />
	<button on:click={createCategoryHandler}>Create</button>
</div>

<style>
	.error {
		color: red;
		font-weight: bold;
	}
</style>
