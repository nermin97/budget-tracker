export class AuthenticationController {

  constructor($log, $location, authenticationService) {
    'ngInject';

    this.email = '';
    this.password = '';
    this.$log = $log;
    this.authenticationService = authenticationService;
    this.$location = $location;

  }

  register() {
    this.authenticationService.register(this.email, this.password)
      .then((response) => {
        localStorage.setItem('token', response.data.token);
        this.$location.path('/dashboard');
      })
      .catch((error) => {
        this.$log.error('Registration failed\n' + angular.toJson(error.data, true));
      });
  }

  login() {
    this.authenticationService.login(this.email, this.password)
      .then((response) => {
        localStorage.setItem('token', response.data.token);
        this.$location.path('/dashboard');
      })
      .catch( (error) => {
        this.$log.error('Login failed\n' + angular.toJson((error.data, true)));
      });
  }

}
