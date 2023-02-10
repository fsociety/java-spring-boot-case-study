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


            $scope.addTodo = () => {
                const data = {
                    "title": $scope.title,
                    "description": $scope.desc,
                    "done": $scope.done ?? false
                }
                $http.post(apiRoot+'/api/v1/todo/add',data)
                .then(({data}) => {
                    $scope.todos.push(data);
                })
                .catch(err => console.error(err));
            }

            $scope.doneTodo = async (e,id) => {
                let done = e.target.checked;
                await $http.post(apiRoot+'/api/v1/todo/update/'+id,{done: done})
                console.log('updated');
            }
        });
})();