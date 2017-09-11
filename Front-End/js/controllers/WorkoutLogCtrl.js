angular.module("WorkoutLog").controller("WorkoutLogCtrl", function ($scope, $http) 
{
$scope.app = "Workout Log";
$scope.total = 0;

var carregarAtividades = function () {
	$http.get("http://localhost:8080/rest-workout/rest/atividades/list").sucess(function (data) {
		$scope.atividades = data;
	});
};

for(var i = 0; i < $scope.atividades.length; i++) $scope.total += parseFloat($scope.atividades[i].tempo);

$scope.tipoesportes = [
	{nomeEsporte: "Run", codigo: 1},
	{nomeEsporte: "Bike", codigo: 2},
	{nomeEsporte: "Swimming", codigo: 3},
];
$scope.adicionaratividade = function (atividade) 
{
	$http.post("http://localhost:8080/rest-workout/rest/atividades/add", atividades).sucess (function (data) {
		$scope.total += parseFloat(atividade.tempo);
		delete $scope.atividade;
		$scope.atividadeForm.$setPristine();
		carregarAtividades();
	})
	
};

$scope.apagaratividades = function (_scope) 
{				
	$scope.atividades = _scope.filter(function (linhaatividade) 
	{					
		if(linhaatividade.selecionado) $scope.total -= parseFloat(linhaatividade.tempo);

		if (!linhaatividade.selecionado) return linhaatividade; 
	});
};
$scope.isatividadeSelecionado = function (atividades) 
{
	return atividades.some(function (atividade) 
	{
		return atividade.selecionado;
	});
};
$scope.ordenarPor = function (campo) 
{
	console.log(campo);
	$scope.criterioDeOrdenacao = campo;				
	$scope.direcaoDaOrdenacao = !$scope.direcaoDaOrdenacao;
};
$scope.pesquisa = function () 
{
	$scope.total = 0;
	for(var i = 0; i < $scope.itensFiltrados.length; i++) 
		$scope.total += parseFloat($scope.itensFiltrados[i].tempo);				
}
});