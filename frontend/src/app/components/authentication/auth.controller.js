export class AuthenticationController {

  constructor($log, $location, toastr, authenticationService) {
    'ngInject';

    this.email = '';
    this.password = '';
    this.$log = $log;
    this.authenticationService = authenticationService;
    this.$location = $location;
    this.toastr = toastr;

  }


  showToastr(message) {
    this.toastr.info(message);
  }


  register() {
    this.authenticationService.register(this.email, this.password)
      .then((response) => {
        localStorage.setItem('token', response.data.token);
        this.$location.path('/dashboard');
      })
      .catch((error) => {
        this.showToastr('registration failed! Please try another email address!');
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
        this.showToastr("Incorrect credentials!");
        this.$log.error('Login failed\n' + angular.toJson((error.data, true)));
      });
  }

}
