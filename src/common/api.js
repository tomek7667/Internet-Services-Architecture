const baseUrl = "http://localhost:8081/api/v1";
const headers = {
	"Content-Type": "application/json",
};

const getCategories = async () => {
	return new Promise(async (resolve, reject) => {
		const response = await fetch(`${baseUrl}/categories`, { headers });
		if (response.ok) {
			const { categories } = await response.json();
			return resolve({
				success: true,
				data: categories,
			});
		} else {
			return resolve({
				success: false,
				data: [],
			});
		}
	});
};

const getGategory = async (id) => {
	return new Promise(async (resolve, reject) => {
		const response = await fetch(`${baseUrl}/categories/${id}`, {
			headers,
		});
		if (response.ok) {
			const category = await response.json();
			return resolve({
				success: true,
				data: category,
			});
		} else {
			return resolve({
				success: false,
				data: {},
			});
		}
	});
};

const deleteCategory = async (id) => {
	return new Promise(async (resolve, reject) => {
		const response = await fetch(`${baseUrl}/categories/${id}`, {
			method: "DELETE",
			headers,
		});
		if (response.ok) {
			return resolve({
				success: true,
			});
		} else {
			return resolve({
				success: false,
			});
		}
	});
};

const getTasks = async (categoryId) => {
	return new Promise(async (resolve, reject) => {
		const response = await fetch(
			`${baseUrl}/categories/${categoryId}/tasks`,
			{
				headers,
			}
		);
		if (response.ok) {
			const { tasks } = await response.json();
			return resolve({
				success: true,
				data: tasks,
			});
		} else {
			return resolve({
				success: false,
				data: [],
			});
		}
	});
};

const getTask = async (taskId) => {
	return new Promise(async (resolve, reject) => {
		const response = await fetch(`${baseUrl}/tasks/${taskId}`, {
			headers,
		});
		if (response.ok) {
			const task = await response.json();
			return resolve({
				success: true,
				data: task,
			});
		} else {
			return resolve({
				success: false,
				data: {},
			});
		}
	});
};

const deleteTask = async (taskId) => {
	return new Promise(async (resolve, reject) => {
		const response = await fetch(`${baseUrl}/tasks/${taskId}`, {
			method: "DELETE",
			headers,
		});
		if (response.ok) {
			return resolve({
				success: true,
			});
		} else {
			return resolve({
				success: false,
			});
		}
	});
};

const createCategory = async (category) => {
	return new Promise(async (resolve, reject) => {
		const response = await fetch(`${baseUrl}/categories`, {
			method: "POST",
			headers,
			body: JSON.stringify(category),
		});
		if (response.ok) {
			return resolve({
				success: true,
			});
		} else {
			return resolve({
				success: false,
			});
		}
	});
};

const createTask = async (task) => {
	return new Promise(async (resolve, reject) => {
		const response = await fetch(`${baseUrl}/tasks`, {
			method: "POST",
			headers,
			body: JSON.stringify(task),
		});
		if (response.ok) {
			return resolve({
				success: true,
			});
		} else {
			return resolve({
				success: false,
			});
		}
	});
};

export {
	getCategories,
	getGategory,
	deleteCategory,
	getTasks,
	getTask,
	deleteTask,
	createCategory,
	createTask,
};
