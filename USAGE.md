# Request mapping `/api/v1/categories`

## Get all categories

`GET /api/v1/categories`
returns
`['category1', 'category2', 'category3']`

## Get specific category

`GET /api/v1/categories/{category uuid}`
returns

```json
{
	"id": "c40c5db1-ea2b-4e2f-9808-613f573c2f5d",
	"name": "category1",
	"description": "description of category1",
	"tasks": [
		{
			"id": "c40c5db1-ea2b-4e2f-9808-613f573c2f5d",
			"name": "task1",
			"description": "description of task1",
			"points": 10,
			"category": "category1",
			"flagHash": "1f40fc92da241694750979ee6cf582f2d5d7d28e18335de05abc54d0560e0f5302860c652bf08d560252aa5e74210546f369fbbbce8c12cfc7957b2652fe9a75"
		},
		{
			"id": "c40c5db1-ea2b-4e2f-9808-613f573c2f5d",
			"name": "task2",
			"description": "description of task2",
			"points": 20,
			"category": "category1",
			"flagHash": "1f40fc92da241694750979ee6cf582f2d5d7d28e18335de05abc54d0560e0f5302860c652bf08d560252aa5e74210546f369fbbbce8c12cfc7957b2652fe9a75"
		}
	]
}
```

## Create category

`POST /api/v1/categories`
with body

```json
{
	"name": "category1",
	"description": "description of category1"
}
```

returns `Created` with path to created category

## Update category

`PUT /api/v1/categories/{category uuid}`
with optional arguments in body:

```json
{
	"name": "category1",
	"description": "description of category1"
}
```

returns `200 OK`

## Delete category

`DELETE /api/v1/categories/{category uuid}`
returns `200 OK`

# Request mapping `/api/v1/tasks`

## Get all tasks

`GET /api/v1/tasks`
returns
`['task1', 'task2', 'task3']`

## Get task by id

`GET /api/v1/tasks/{task uuid}`

returns

```json
{
	"id": "140c5db1-ea2b-4e2f-9808-613f573c2f5d",
	"name": "task1",
	"description": "Task 1 description",
	"points": 10,
	"category": "category1",
	"flagHash": "1f40fc92da241694750979ee6cf582f2d5d7d28e18335de05abc54d0560e0f5302860c652bf08d560252aa5e74210546f369fbbbce8c12cfc7957b2652fe9a75"
}
```

## Create task

`POST /api/v1/tasks`
with body

```json
{
	"name": "task1",
	"description": "Task 1 description",
	"points": 10,
	"category": "c40c5db1-ea2b-4e2f-9808-613f573c2f5d",
	"flagHash": "flag{S3cr3t_fl4g}"
}
```

returns `Created` with path to created task and body:

```json
{
	"id": "1f40fc92-da24-1694-7509-79ee6cf582f2",
	"name": "task1",
	"description": "Task 1 description",
	"points": 10,
	"category": "category1",
	"flagHash": "1f40fc92da241694750979ee6cf582f2d5d7d28e18335de05abc54d0560e0f5302860c652bf08d560252aa5e74210546f369fbbbce8c12cfc7957b2652fe9a75"
}
```

## Update task

`PUT /api/v1/tasks/{task uuid}`
with optional arguments in body:

```json
{
	"name": "task1",
	"description": "Task 1 description",
	"points": 10,
	"category": "category1",
	"flag": "flag{S3cr3t_fl4g}"
}
```

returns `200 OK`

## Delete task

`DELETE /api/v1/tasks/{task uuid}`
returns `200 OK`
