
export function routerConfig ($routeProvider) {
  'ngInject';
  $routeProvider
    .when('/', {
      templateUrl: 'app/main/main.html',
      controller: 'MainController',
      controllerAs: 'main'
    })
    .when('/login', {
      templateUrl: 'app/components/authentication/login.html',
      controller: 'authController',
      controllerAs: 'auth'
    })
    .when('/register', {
      templateUrl: 'app/components/authentication/register.html',
      controller: 'authController',
      controllerAs: 'auth'
    })
    .when('/dashboard', {
      templateUrl: 'app/components/dashboard/dashboard.html',
      controller: 'dashController',
      controllerAs: 'dash'
    })
    .otherwise({
      redirectTo: '/'
    });
}
