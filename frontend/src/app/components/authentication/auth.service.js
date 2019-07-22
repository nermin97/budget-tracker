export class AuthenticationService {
  constructor ($http) {
    'ngInject';

    this.$http = $http;
    this.apiRegister = 'http://localhost:8080/backend/api/auth/register/';
    this.apiLogin = 'http://localhost:8080/backend/api/auth/login/';
  }

  register(emailInput, passwordInput) {
    let credentials =  {
      email: emailInput,
      password: passwordInput
    };
    return this.$http.post(this.apiRegister, credentials);
  }

  login(emailInput, passwordInput) {
    let credentials = {
      email: emailInput,
      password: passwordInput
    };
    return this.$http.post(this.apiLogin, credentials);
  }
}
