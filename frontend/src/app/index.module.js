import { config } from './index.config';
import { routerConfig } from './index.route';
import { runBlock } from './index.run';
import { MainController } from './main/main.controller';
import { AuthenticationService } from "./components/authentication/auth.service";
import { AuthenticationController } from "./components/authentication/auth.controller";
import {IncomeService} from "./components/dashboard/incomes/income.service";
import {DashboardController} from "./components/dashboard/dashboard.controller";
import { NavbarDirective } from "./components/navbar/navbar.directive";
import {ExpenseService} from "./components/dashboard/expenses/expense.service";

// eslint-disable-next-line angular/controller-name
angular.module('frontend', ['ngSanitize', 'ngMessages', 'ngResource', 'ngRoute', 'ui.bootstrap', 'toastr'])
  .config(config)
  .config(routerConfig)
  .run(runBlock)
  .service('incomeService', IncomeService)
  .service('expenseService', ExpenseService)
  .service('authenticationService', AuthenticationService)
  .controller('MainController', MainController)
  .controller('authController', AuthenticationController)
  .controller('dashController', DashboardController)
  .directive('acmeNavbar', NavbarDirective);
