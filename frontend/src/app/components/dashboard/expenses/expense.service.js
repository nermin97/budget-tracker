export class ExpenseService {
  constructor ($http) {
    'ngInject';

    this.token = 'token';
    this.forNote = '/note/';
    this.$http = $http;
    this.apiExpenses = 'https://backend-api-pragmatio.herokuapp.com/api/me/expense/';
  }

  //expenses
  getExpenses() {
    this.setAuthorization();
    return this.$http.get(this.apiExpenses);
  }

  saveExpense(expenseData) {
    this.setAuthorization();
    return this.$http.post(this.apiExpenses, expenseData);
  }

  updateExpense(expenseId, expenseData) {
    this.setAuthorization();
    return this.$http.post(this.apiExpenses + expenseId, expenseData);
  }

  deleteExpense(expenseId) {
    this.setAuthorization();
    return this.$http.delete(this.apiExpenses + expenseId);
  }

  //note
  addNote(expenseId, note) {
    this.setAuthorization();
    return this.$http.post(this.apiExpenses + expenseId + this.forNote, note);
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


