<script>
    export let currentRoute;
    export let currentCategory;
    export let currentTask
    export let backRoute;

    import { onMount } from 'svelte';
	import { getTasks } from '../../common/api';
	import Task from '../Task.svelte';
    
    let tasks = [];

    onMount(async () => {
        let localTasks = await getTasks(currentCategory.id);
        if (localTasks.success) {
            tasks = localTasks.data;
        } else {
            alert("Error occured, is tasks server running?")
        }
    })
</script>

<div>
    <p>Inside of: <b>{currentCategory.name}</b></p>
    <div class="categoryDescription">{currentCategory.description}</div>
    {#each tasks as task}
        <Task bind:task bind:currentRoute bind:currentTask bind:tasks bind:backRoute />
    {/each}

</div>

<style>
    .categoryDescription {
        margin: 20px;
    }
</style>