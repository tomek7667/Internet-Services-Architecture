<script>
	export let currentRoute;
	export let backRoute;
	export let categories;
	export let categoryToEdit = {
		name: "",
		description: "",
	};
	import { editCategory, getCategories } from "../../common/api.js";

	let oldCategoryName = categoryToEdit.name;

	let error = {
		name: "",
		description: "",
	};

	const createCategoryHandler = async () => {
		let isValid = true;
		if (categoryToEdit.name === "") {
			error.name = "Name is required";
			isValid = false;
		} else {
			error.name = "";
		}
		if (categoryToEdit.description === "") {
			error.description = "Description is required";
			isValid = false;
		} else {
			error.description = "";
		}
		if (!isValid) {
			return;
		}
		const { success } = await editCategory(categoryToEdit);
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
	<p>Editing <b>{oldCategoryName}</b></p>
	<p>Name of the category:</p>
	<span class="error">{error.name}</span>
	<input type="text" bind:value={categoryToEdit.name} />
	<p>Description of the category:</p>
	<span class="error">{error.description}</span>
	<textarea type="text" bind:value={categoryToEdit.description} />
	<br />
	<br />
	<button on:click={createCategoryHandler}>Submit</button>
</div>

<style>
	.error {
		color: red;
		font-weight: bold;
	}

	textarea {
		width: 500px;
		height: 200px;
	}
</style>
