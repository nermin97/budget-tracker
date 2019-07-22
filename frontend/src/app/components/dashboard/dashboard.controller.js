export class DashboardController {

  constructor($log, $location, $rootScope, toastr, incomeService, expenseService) {
    'ngInject'

    this.incomeService = incomeService;
    this.expenseService = expenseService;

    this.$log = $log;
    this.$location = $location;
    this.toastr = toastr;
    this.noteUrl = 'notePopover.html';

    this.incomeList = [];
    this.expenseList = [];
    this.getIncomes();
    this.getExpenses();

    this.type = 'Select type';
    this.isAddingIncome = null;
    this.isAddingExpense = null;
    this.tableData = {
      id: null,
      description: '',
      category: 1,
      amount: 10.0
    };
    this.noteData = {
      text: '',
      expenseId: null
    };
    this.isVisible = false;
  }

  showToastr(message) {
    this.toastr.info(message);
  }

  getIncomes() {
    this.incomeService.getIncomes()
      .then( (response) => {
        this.incomeList =  response.data || [];
      })
      .catch((error) => {
        localStorage.clear();
        this.$log.error('Getting incomes failed\n' + angular.toJson(error.data, true));
        this.$location.path('/');
      });
  }

  getExpenses() {
    this.expenseService.getExpenses()
      .then((response) => {
        this.expenseList = response.data;
      })
      .catch((error) => {
        localStorage.clear();
        this.$log.error('Getting expenses failed\n' + angular.toJson(error.data, true));
        this.$location.path('/');
      });
  }

  setOnAddingIncome() {
    this.isVisible = true;
    this.isAddingIncome = true;
    this.type = 'Adding Income';
    this.tableData.id = null;
    this.tableData.description = '';
    this.tableData.category = 1;
    this.tableData.amount = 0;
  }

  setOnUpdatingIncome(income) {
    this.isVisible = true;
    this.isAddingIncome = false;
    this.type = 'Updating Income';
    this.tableData.id = income.id;
    this.tableData.description = income.description;
    this.tableData.category = parseInt(income.category);
    this.tableData.amount = parseFloat(income.amount);
  }

  setOnAddingExpense() {
    this.isVisible = true;
    this.isAddingExpense = true;
    this.type = 'Adding Expense';
    this.tableData.id = null;
    this.tableData.description = '';
    this.tableData.category = 1;
    this.tableData.amount = 0;
  }

  setOnUpdatingExpense(expense) {
    this.isVisible = true;
    this.isAddingExpense = false;
    this.type = 'Updating Income';
    this.tableData.id = expense.id;
    this.tableData.description = expense.description;
    this.tableData.category = parseInt(expense.category);
    this.tableData.amount = parseFloat(expense.amount);
  }

  addNewIncome() {
    this.incomeService.saveIncome(this.tableData)
      .then(() => {
        this.getIncomes()
        this.setBackData();
      })
      .catch((error) => {
          localStorage.clear();
        localStorage.clear();
          this.$log.error('Adding income failed\n' + angular.toJson(error.data, true));
          this.$location.path('/');
        });
  }

  updateIncome() {
    this.incomeService.updateIncome(this.tableData.id, this.tableData)
      .then( () => {
        this.getIncomes();
        this.setBackData();
      })
      .catch((error) => {
        localStorage.clear();
        this.$log.error('Updating income failed\n' + angular.toJson(error.data, true));
        this.$location.path('/');
      });
  }

  addNewExpense() {
    this.expenseService.saveExpense(this.tableData)
      .then(() => {
        this.getExpenses();
        this.setBackData();
      })
      .catch((error) => {
        localStorage.clear();
        this.$log.error('Adding expense failed\n' + angular.toJson(error.data, true));
        this.$location.path('/');
      });
  }

  updateExpense() {
    this.expenseService.updateExpense(this.tableData.id, this.tableData)
      .then( () => {
        this.getExpenses();
        this.setBackData();
      })
      .catch((error) => {
        localStorage.clear();
        this.$log.error('Updating expense failed\n' + angular.toJson(error.data, true));
        this.$location.path('/');
      });
  }

  submit() {
    // validating data before applying changes
    if(!this.validateData())
      return;

    if(this.isAddingIncome != null) {
      if(this.isAddingIncome) {
        this.addNewIncome();
      }
      else {
        this.updateIncome();
      }
    }
    else if(this.isAddingExpense != null) {
      if(this.isAddingExpense) {
        this.addNewExpense();
      }
      else {
        this.updateExpense();
      }
    }
  }

  deleteIncome(income) {
    this.incomeService.deleteIncome(income.id)
      .then(() => {
        this.getIncomes();
        this.setBackData();
      })
      .catch((error) => {
        localStorage.clear();
        this.$log.error('Deleting income failed\n' + angular.toJson(error.data, true));
        this.$location.path('/');
      });
  }

  deleteExpense(expense) {
    this.expenseService.deleteExpense(expense.id)
      .then(() => {
        this.getExpenses();
        this.setBackData();
      })
      .catch((error) => {
        localStorage.clear();
        this.$log.error('Deleting expense failed\n' + angular.toJson(error.data, true));
        this.$location.path('/');
      });
  }

  validateData() {
    if(this.tableData.description == '') {
      this.showToastr('Type in description!');
      return false;
    }
    if(this.tableData.category < 1 || this.tableData.category > 9) {
      this.showToastr('Category has to be between 1 and 9 including 1 and 9!');
      return false;
    }
    if(this.tableData.amount < 0) {
      this.showToastr('Amount can not be below 10!');
      return false;
    }
    return true;
  }

  setBackData() {
    this.isVisible = false;
    this.type = 'Select type';
    this.isAddingIncome = null;
    this.isAddingExpense = null;
    this.tableData = {
      id: null,
      description: '',
      category: 1,
      amount: 0
    }
  }

  setOnAddingNote(expenseId) {
    this.noteData.expenseId = expenseId;
  }

  resetOnAddingNote() {
    this.noteData.expenseId = null;
    this.noteData.text = '';
  }

  addNote() {
    if(this.noteData.text == '') return;
    this.expenseService.addNote(this.noteData.expenseId, this.noteData)
      .then(() => {
        this.getExpenses();
        this.resetOnAddingNote();
      })
      .catch((error) => {
        localStorage.clear();
        this.$log.error('Adding note failed\n' + angular.toJson(error.data, true));
        this.$location.path('/');
      });
  }

  logout() {
    localStorage.clear();
    this.$location.path('/');
  }
}
