<script>
    export let category;
    export let categories;
    export let currentRoute;
    export let currentCategory;
    export let backRoute;

    import {deleteCategory, getGategory} from '../common/api.js';

    const deleteCategoryHandler = async () => {
        const result = await deleteCategory(category.id);
        if (result.success) {
            categories = categories.filter(c => c.id !== category.id);
        } else {
            alert("Error occured, is categories server running?");
        }
    }

    const getCategoryHandler = async () => {
        const result = await getGategory(category.id);
        if (result.success) {
            backRoute = currentRoute;
            currentRoute = "/category";
            currentCategory = result.data;
        } else {
            alert("Error occured, is categories server running?");
        }
    }
</script>

<div>
    <h3>{category.name}</h3>
    <div>
        <button on:click={getCategoryHandler}>Enter category</button>
        <button on:click={deleteCategoryHandler}>Remove category</button>
    </div>
</div>