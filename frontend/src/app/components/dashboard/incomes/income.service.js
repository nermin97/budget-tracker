export class IncomeService {
  constructor ($http) {
    'ngInject';

    this.token = 'token';
    this.$http = $http;
    this.apiIncomes = 'https://backend-api-pragmatio.herokuapp.com/api/me/income/';
  }

  getIncomes() {
    this.setAuthorization();
    return this.$http.get(this.apiIncomes);

  }

  saveIncome(incomeData) {
    this.setAuthorization();
    return this.$http.post(this.apiIncomes, incomeData);
  }

  updateIncome(incomeId, incomeData) {
    this.setAuthorization();
    return this.$http.post(this.apiIncomes + incomeId, incomeData);
  }

  deleteIncome(incomeId) {
    this.setAuthorization();
    return this.$http.delete(this.apiIncomes + incomeId);
  }

  getToken() {
    return localStorage.getItem(this.token);
  }

  setAuthorization() {
    const token = this.getToken();
    if(token) {
      this.$http.defaults.headers.common.Authorization = 'Bearer ' + token;
    }
  }
}


