<script>
  import { getCategories } from './common/api.js';
  import { onMount} from 'svelte';
	import Category from './lib/Category.svelte';
	import MainCategory from './lib/Category/MainCategory.svelte';
	import MainTask from './lib/Task/MainTask.svelte';

  let categories = [];
  let currentRoute = "/";
  let backRoute = "/";
  let currentCategory = {};
  let currentTask = {};
  let pointsGot = 0;

  onMount(async () => {
    let localCategories = await getCategories();
    if (localCategories.success) {
      categories = localCategories.data;
    } else {
      alert("Error occured, is categories server running?")
    }
  })

</script>

<main>
  <h1>CTF Task - ISA - 184571</h1>
  {#if backRoute !== "/"}
    <button on:click={() => {currentRoute = backRoute; backRoute = '/';}}>Go back</button>
  {/if}
  {#if currentRoute === "/category"}
    <MainCategory bind:currentRoute bind:currentCategory bind:currentTask bind:backRoute />
  {:else if currentRoute === "/task"}
    <MainTask bind:currentRoute bind:currentTask bind:backRoute />
  {:else}
    {#each categories as category}
      <Category category={category} bind:categories={categories} bind:currentRoute bind:currentCategory bind:backRoute />
    {/each}
  {/if}
</main>
<footer>
  <span>Created by: Tomasz Dąbrowski</span>
  {#if currentRoute !== "/"}
    <button on:click={() => {backRoute = currentRoute; currentRoute = "/";}}>Go Home</button>
  {/if}
</footer>

<style>
  footer {
    margin-top: 20px;
  }
</style>
