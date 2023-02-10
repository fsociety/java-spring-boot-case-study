(function () {
    angular.module("todoApp",[])
        .controller("TodoController", function ($scope, $http) {
            const apiRoot = 'http://localhost:5500';

            //get all todos
            $http.get(apiRoot+'/api/v1/todos')
                .then(({data}) => {
                    $scope.todos = data;
                })
                .catch(err => console.error(err));


            $scope.insertOrUpdate = (id = null) => {
                const data = {
                    "title": $scope.title,
                    "description": $scope.desc,
                    "done": $scope.done ?? false
                }
                let post_url = apiRoot+'/api/v1/todo/add';
                if(id) post_url = apiRoot+'/api/v1/todo/update/'+id;

                $http.post(post_url,data)
                .then(({data}) => {
                    if(id === null){
                        $scope.todos = [
                            data,
                            ...$scope.todos
                        ];
                    }else{
                        $scope.todos = $scope.todos.map(e => e.id === id ? data : e);
                    }
                })
                .catch(err => console.error(err));
            }

            $scope.doneTodo = async (e,id) => {
                let done = e.target.checked;
                await $http.post(apiRoot+'/api/v1/todo/update/'+id,{done: done})
                console.log('updated');
            }

            $scope.updateForm = (id) => {
                let todo = $scope.todos.find(e => e.id === id);
                $scope.title = todo.title;
                $scope.desc = todo.description;
                $scope.done = todo.done;
                $scope.todoId = id;
            }

            $scope.deleteTodo = async (id) => {
                await $http.delete(apiRoot+'/api/v1/todo/destroy/'+id);
                console.log('deleted');
                let el = document.querySelector(`div[data-todo="${id}"]`);
                el.classList.add('remove-transition');
                setTimeout(() => {
                    $scope.$apply(() => {
                        $scope.todos = $scope.todos.filter(e => e.id !== id);
                    });
                },600)
            }

        });
})();