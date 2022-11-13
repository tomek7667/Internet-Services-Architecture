<script>
	export let currentRoute;
	export let currentCategory;
	export let backRoute;
	export let categories;
	import { createCategory, getCategories } from "../../common/api.js";

	let category = {
		name: "",
		description: "",
	};

	const createCategoryHandler = async () => {
		await createCategory(category);
		let localCategories = await getCategories();
		if (localCategories.success) {
			categories = localCategories.data;
		} else {
			alert("Error occured, is categories server running?");
		}
		backRoute = currentRoute;
		currentRoute = "/";
	};
</script>

<div>
	<h3>Create Category</h3>
	<p>Name of the category:</p>
	<input type="text" bind:value={category.name} />
	<p>Description of the category:</p>
	<input type="text" bind:value={category.description} />
	<button on:click={createCategoryHandler}>Create</button>
</div>
