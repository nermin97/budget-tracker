export class AuthenticationService {
  constructor ($http) {
    'ngInject';

    this.$http = $http;
    this.apiRegister = 'https://backend-api-pragmatio.herokuapp.com/api/auth/register/';
    this.apiLogin = 'https://backend-api-pragmatio.herokuapp.com/api/auth/login/';
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
