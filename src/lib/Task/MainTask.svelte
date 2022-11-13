<script>
	import { sha512 } from "../../common/sha512.js";
	export let currentRoute;
	export let currentTask;
	export let backRoute;
	let userFlag = "";

	const checkFlag = async () => {
		const flagHash = currentTask.flagHash;
		const trial = await sha512(userFlag.trim());
		if (trial === flagHash) {
			alert("That is a correct flag! Nice :)");
		} else {
			alert("Wrong flag! :( Try again!");
		}
	};
</script>

<div>
	<p>Name: <b>{currentTask.name}</b></p>
	<p>Description: <b>{currentTask.description}</b></p>
	<p>Points: <b>{currentTask.points}</b></p>
	<p>Check if you have found correct flag:</p>
	<div>
		<input type="password" bind:value={userFlag} />
		<button on:click={checkFlag}>Check</button>
	</div>
</div>
