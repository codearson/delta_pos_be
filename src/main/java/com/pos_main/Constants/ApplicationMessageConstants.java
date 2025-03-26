package com.pos_main.Constants;

public interface ApplicationMessageConstants {
	public interface ServiceErrorMessages {

		//jwt Security
		String EX_JWT_INVALID = "ex.jwt.invalid";
		String EX_JWT_EXPIRED = "ex.jwt.expired";

		String ERR_SAVE_USER_DETAILS = "err.save.user.details";
		String EX_SAVE_USER_DETAILS = "ex.save.user.details";
		String ERR_SAVE_USER_ROLE_DETAILS = "err.save.user.role.details";
		String EX_SAVE_USER_ROLE_DETAILS = "ex.save.user.role.details";
		String ERR_RETRIEVE_ALL_BRANCH_DETAILS = "err.get.all.branch.details";
		String EX_RETRIEVE_ALL_BRANCH_DETAILS = "ex.get.all.branch.details";
		String ERR_SAVE_RE_BRANCH_DETAILS = "err.save.branch.details";
		String EX_SAVE_RE_BRANCH_DETAILS = "ex.save.branch.details";
		String ERR_RETRIEVE_BRANCH_BY_SBU_ID = null;
		String EX_RETRIEVE_BRANCH_BY_SBU_ID = null;
		String ERR_RETRIEVE_ALL_CATRGORY_DETAILS = "err.get.all.category.details";
		String EX_RETRIEVE_ALL_CATRGORY_DETAILS = "ex.get.all.category.details";
		String ERR_SAVE_PRODUCT_CATRGORY_DETAILS = "err.save.category.details";
		String EX_SAVE_PRODUCT_CATRGORY_DETAILS = "ex.save.category.details";
		String ERR_SAVE_RE_CATRGORY_DETAILS = "err.save.product.details";
		String EX_SAVE_RE_CATRGORY_DETAILS = "ex.save.product.details";
		String ERR_RETRIEVE_ALL_PRODUCT_DETAILS = "err.get.all.product.details";
		String EX_RETRIEVE_ALL_PRODUCT_DETAILS = "ex.get.all.product.details";
		String ERR_RETRIEVE_ALL_USER_DETAILS = "err.get.all.user.details";
		String EX_RETRIEVE_ALL_USER_DETAILS = "ex.get.all.user.details";
		String ERR_SAVE_PAYMENT_METHOD_DETAILS = "err.save.payment.method.details";
		String EX_SAVE_PAYMENT_METHOD_DETAILS = "ex.save.payment.method.details";
		String ERR_SAVE_DISCOUNT_DETAILS = "err.save.discount.details";
		String EX_SAVE_DISCOUNT_DETAILS = "ex.save.discount.details";
		String ERR_UPDATE_PRODUCT_CATRGORY_DETAILS="err.update.category.details";
		String EX_UPDATE_PRODUCT_CATRGORY_DETAILS="ex.update.category.details";
		String ERR_RETRIEVE_CATEGORY_BY_NAME="err.gel.all.by.name.category.details";
		String EX_RETRIEVE_CATEGORY_BY_NAME="ex.gel.all.by.name.category.details";
		String ERR_RETRIEVE_USER_BY_NAME = "err.get.all.user.details.by.name";
		String EX_RETRIEVE_USER_BY_NAME = "ex.get.all.user.details.by.name";
		String ERR_SAVE_TAX_DETAILS = "err.save.tax.details";
		String EX_SAVE_TAX_DETAILS = "ex.save.tax.details";
		String ERR_UPDATE_BRANCH_DETAILS = "err.update.branch.details";
		String EX_UPDATE_BRANCH_DETAILS = "ex.update.branch.details";
		String ERR_RETRIEVE_BRANCH_BY_NAME = "err.retrieve.branch.by.name";
		String EX_RETRIEVE_BRANCH_BY_NAME = "ex.retrieve.branch.by.name";
		String ERR_RETRIEVE_USER_BY_ID = "err.get.all.user.details.by.id";
		String EX_RETRIEVE_USER_BY_ID = "ex.get.all.user.details.by.id";
		String ERR_RETRIEVE_USER_BY_ROLE = "err.get.all.user.details.by.role";
		String EX_RETRIEVE_USER_BY_ROLE = "ex.get.all.user.details.by.role";
		String ERR_UPDATE_RE_USER_DETAILS = "err.update.user.details";
		String EX_UPDATE_RE_USER_DETAILS = "ex.update.user.details";
		String ERR_RETRIEVE_PRODUCT_BY_BARCODE = "err.get.all.product.details.by.barcode";
		String EX_RETRIEVE_PRODUCT_BY_BARCODE = "ex.get.all.product.details.by.barcode";
		String ERR_RETRIEVE_CUSTOMER_BY_SEARCH = "err.retrieve.customer.by.search";
		String EX_RETRIEVE_USER_CUSTOMER_BY_SEARCH = "ex.retrieve.customer.by.search";
		String ERR_RETRIEVE_ALL_CUSTOMER_DETAILS = "err.get.all.customer.details";
		String EX_RETRIEVE_ALL_CUSTOMER_DETAILS = "ex.get.all.customer.details";
		String ERR_SAVE_CUSTOMER_DETAILS = "err.save.customer.details";
		String EX_SAVE_CUSTOMER_DETAILS = "ex.save.customer.details";
		String ERR_UPDATE_USER_STATUS = "err.update.user.status";
		String EX_UPDATE_USER_STATUS = "ex.update.user.status";
		String ERR_UPDATE_CATEGORY_STATUS = "err.update.category.status";
		String EX_UPDATE_CATEGORY_STATUS = "ex.update.category.status";
		String ERR_RETRIEVE_PRODUCT_BY_NAME = "err.get.product.by.name";
		String EX_RETRIEVE_PRODUCT_BY_NAME = "ex.get.product.by.name";
		String ERR_UPDATE_RE_PRODUCT_DETAILS = "err.update.product.details";
		String EX_UPDATE_RE_PRODUCT_DETAILS = "ex.update.product.details";
		String ERR_SAVE_SUPPLIER_DETAILS = "err.save.supplier.details";
		String EX_SAVE_SUPPLIER_DETAILS = "ex.save.supplier.details";
		String ERR_UPDATE_PRODUCT_STATUS = "err.update.product.status";
		String EX_UPDATE_PRODUCT_STATUS = "ex.update.product.status";
		String ERR_RETRIEVE_SUPPLIER_BY_NAME = "err.retrieve.supplier.by.name";
		String EX_RETRIEVE_SUPPLIER_BY_NAME = "ex.retrieve.supplier.by.name";
		String ERR_UPDATE_SUPPLIER_STATUS = "err.update.supplier.status";
		String EX_UPDATE_SUPPLIER_STATUS = "ex.update.supplier.status";
		String ERR_UPDATE_RE_SUPPLIER_DETAILS = "err.update.supplier.details";
		String EX_UPDATE_RE_SUPPLIER_DETAILS = "ex.update.supplier.details";
		String ERR_RETRIEVE_SUPPLIER_BY_ID = "err.get.all.supplier.details.by.id";
		String EX_RETRIEVE_SUPPLIER_BY_ID = "ex.get.all.supplier.details.by.id";
		String ERR_UPDATE_BRANCH_STATUS = "err.update.branch.status";
		String EX_UPDATE_BRANCH_STATUS = "ex.update.branch.status";
		String ERR_SAVE_TRANSACTION_DETAILS = "err.save.transaction.details";
		String EX_SAVE_TRANSACTION_DETAILS = "ex.save.transaction.details";
		String ERR_RETRIEVE_PRODUCT_BY_ID = "err.get.all.product.details.by.id";
		String EX_RETRIEVE_PRODUCT_BY_ID = "ex.get.all.product.details.by.id";
		String ERR_SAVE_STOCK_DETAILS = "err.save.stock.details";
		String EX_SAVE_STOCK_DETAILS = "ex.save.stock.details";
		String ERR_RETRIEVE_BRANCH_BY_ID = "err.get.all.branch.details.by.id";
		String EX_RETRIEVE_BRANCH_BY_ID = "ex.get.all.branch.details.by.id";
		String ERR_RETRIEVE_ALL_STOCK_DETAILS = "err.retrieve.all.stock.details";
		String EX_RETRIEVE_ALL_STOCK_DETAILS = "ex.retrieve.all.stock.details";
		String ERR_UPDATE_STOCK_DETAILS = "err.update.stock.details";
		String EX_UPDATE_STOCK_DETAILS = "ex.update.stock.details";
		String ERR_UPDATE_STOCK_STATUS = "err.update.stock.status";
		String EX_UPDATE_STOCK_STATUS = "ex.update.stock.status";
		String ERR_UPDATE_TRANSACTION_DETAILS  = "err.update.transaction.details";
		String EX_UPDATE_TRANSACTION_DETAILS  = "ex.update.transaction.details";
		String ERR_RETRIEVE_STOCK_BY_ID = "err.retrieve.stock.by.id";
		String EX_RETRIEVE_STOCK_BY_ID = "ex.retrieve.stock.by.id";
		String ERR_RETRIEVE_ALL_SUPPLIER_DETAILS = "err.retrieve.all.supplier.details";
		String EX_RETRIEVE_ALL_SUPPLIER_DETAILS = "ex.retrieve.all.supplier.details";
		String ERR_RETRIEVE_CUSTOMER_BY_ID = "err.get.all.customer.details.by.id";
		String EX_RETRIEVE_CUSTOMER_BY_ID = "ex.get.all.customer.details.by.id";
		String ERR_RETRIEVE_TRANSACTION_BY_BRANCH_ID = "err.retrieve.transaction.by.branch.id";
		String EX_RETRIEVE_TRANSACTION_BY_BRANCH_ID = "ex.retrieve.transaction.by.branch.id";
		String ERR_RETRIEVE_TRANSACTION_DETAILS_LIST_BY_TRANSACTION_ID = "err.retrieve.transaction.details.list.by.transaction.id";
		String EX_RETRIEVE_TRANSACTION_DETAILS_LIST_BY_TRANSACTION_ID = "ex.retrieve.transaction.details.list.by.transaction.id";
		String ERR_RETRIEVE_TRANSACTION_PAYMENT_METHOD_BY_TRANSACTION_ID = "err.retrieve.transaction.payment.method.by.transaction.id";
		String EX_RETRIEVE_TRANSACTION_PAYMENT_METHOD_BY_TRANSACTION_ID = "ex.retrieve.transaction.payment.method.by.transaction.id";
		String ERR_RETRIEVE_TRANSACTION_BY_PAYMENT_METHOD_ID = "err.retrieve.transaction.by.payment.method.id";
		String EX_RETRIEVE_TRANSACTION_BY_PAYMENT_METHOD_ID = "ex.retrieve.transaction.by.payment.method.id";
		String ERR_RETRIEVE_ALL_TRANSACTION = "err.get.all.transaction.details";
		String EX_RETRIEVE_ALL_TRANSACTION = "ex.get.all.transaction.details";
		String ERR_RETRIEVE_TRANSACTION_BY_ID = "err.retrieve.transaction.by.id";
		String EX_RETRIEVE_TRANSACTION_BY_ID = "ex.retrieve.transaction.by.id";
		String ERR_RETRIEVE_TRANSACTION_BY_USER_ID = "err.retrieve.transaction.by.user.id";
		String EX_RETRIEVE_TRANSACTION_BY_USER_ID = "ex.retrieve.transaction.by.user.id";
		String ERR_RETRIEVE_STOCK_BY_PRODUCT_CATEGORY_ID = "err.retrieve.stock.by.product.category.id";
		String EX_RETRIEVE_STOCK_BY_PRODUCT_CATEGORY_ID = "ex.retrieve.stock.by.product.category.id";
		String ERR_SAVE_USER_LOGS_DETAILS = "err.save.userlogs.details";
		String EX_SAVE_USER_LOGS_DETAILS = "ex.save.userlogs.details";
		String ERR_SAVE_SHOP_DETAILS = "err.save.shopdetails.details";
		String EX_SAVE_SHOP_DETAILS = "ex.save.shopdetails.details";
		String ERR_SAVE_SHIFTS_DETAILS = "err.save.shifts.detail";
		String EX_SAVE_SHIFTS_DETAILS = "ex.save.shifts.detail";
		String ERR_RETRIEVE_TRANSACTION_BY_STATUS = "err.retrieve.transaction.by.status";
		String EX_RETRIEVE_TRANSACTION_BY_STATUS = "ex.retrieve.transaction.by.status";
		String ERR_LOGIN_USER_LOGS_DETAILS = "err.login.userlogs.detail";
		String EX_LOGIN_USER_LOGS_DETAILS = "ex.login.userlogs.detail";
		String ERR_RETRIEVE_STOCK_BY_PRODUCT_ID = "err.retrieve.stock.by.product.id";
		String EX_RETRIEVE_STOCK_BY_PRODUCT_ID = "ex.retrieve.stock.by.product.id";
		String ERR_FORGOT_PASSWORD_EMAIL = "err.forgot.password.email";
		String EX_FORGOT_PASSWORD_EMAIL = "ex.forgot.password.email";
		String ERR_RESET_PASSWORD = "err.reset.password";
		String EX_RESET_PASSWORD = "ex.reset.password";
		String ERR_RETRIEVE_TRANSACTION_BY_PRODUCT_ID = "err.retrieve.transaction.by.product.id";
		String EX_RETRIEVE_TRANSACTION_BY_PRODUCT_ID = "ex.retrieve.transaction.by.product.id";
		String ERR_RETRIEVE_STOCK_BY_QUANTITYRANGE = "err.retrieve.stock.by.quantityrange";
		String EX_RETRIEVE_STOCK_BY_QUANTITYRANGE = "ex.retrieve.stock.by.quantityrange";
		String ERR_RETRIEVE_TRANSACTION_BY_CUSTOMER_ID = "err.retrieve.transaction.by.customer.id";
		String EX_RETRIEVE_TRANSACTION_BY_CUSTOMER_ID = "ex.retrieve.transaction.by.customer.id";
		String ERR_RETRIEVE_TRANSACTION_BY_DATERANGE= "err.retrieve.transaction.by.daterange";
		String EX_RETRIEVE_TRANSACTION_BY_DATERANGE= "ex.retrieve.transaction.by.daterange";
		String ERR_RETRIEVE_USER_BY_EMAIL_ADDRESS = "err.retrieve.user.by.email.address";
		String EX_RETRIEVE_USER_BY_EMAIL_ADDRESS = "ex.retrieve.user.by.email.address";
		String ERR_RETRIEVE_TAX_BY_NAME = "err.retrieve.get.all.tax.by.percentage";
		String EX_RETRIEVE_TAX_BY_NAME = "ex.retrieve.get.all.tax.by.percentage";
		String ERR_RETRIEVE_ALL_TAX_DETAILS = "err.retrieve.all.tax.details";
		String EX_RETRIEVE_ALL_TAX_DETAILS = "ex.retrieve.all.tax.details";
		String ERR_UPDATE_TAX_DETAILS = "err.update.tax.details";
        String EX_UPDATE_TAX_DETAILS = "ex.update.tax.details";
        String ERR_UPDATE_TAX_STATUS = "err.update.tax.status";
        String EX_UPDATE_TAX_STATUS = "ex.update.tax.status";
		String ERR_SAVE_PURCHASE_LIST_DETAILS = "err.save.purchase.list.details";
		String EX_SAVE_PURCHASE_LIST_DETAILS = "ex.save.purchase.list.details"; 
		String ERR_RETRIEVE_ALL_DETAILS = "err.retrieve.all.details";
		String EX_RETRIEVE_ALL_DETAILS = "ex.retrieve.all.details";
		String ERR_DELETE_ALL_PURCHASE_LIST_DETAILS = "err.delete.all.purchase.list.details";
		String EX_DELETE_ALL_PURCHASE_LIST_DETAILS = "ex.delete.all.purchase.list.details";
        String ERR_UPDATE_CUSTOMER_DETAILS = "err.update.customer.details";
        String EX_UPDATE_CUSTOMER_DETAILS = "ex.update.customer.details";
        String ERR_RETRIEVE_CUSTOMER_BY_MOBILE_NUMBER = "err.retrieve.customer.by.mobile.number";
        String EX_RETRIEVE_CUSTOMER_BY_MOBILE_NUMBER = "ex.retrieve.customer.by.mobile.number";
        String ERR_UPDATE_CUSTOMER_STATUS = "err.update.customer.status";
        String EX_UPDATE_CUSTOMER_STATUS = "ex.update.customer.status";
        String ERR_RETRIEVE_ALL_USERROLE_DETAILS = "err.get.all.user.details";
		String EX_RETRIEVE_ALL_USERROLE_DETAILS = "ex.get.all.user.details";
		String ERR_SAVE_COUNTRY_DETAILS = "err.save.country.details";
        String EX_SAVE_COUNTRY_DETAILS = "ex.save.country.details";
        String ERR_UPDATE_USER_PASSWORD = "err.update.user.password";
        String EX_UPDATE_USER_PASSWORD = "ex.update.user.password";
        String ERR_RETRIEVE_TRANSACTION_BY_X_REPORT = "err.retrieve.transaction.by.x.report";
        String EX_RETRIEVE_TRANSACTION_BY_X_REPORT = "ex.retrieve.transaction.by.x.report";
        String ERR_RETRIEVE_TRANSACTION_BY_Y_REPORT = "err.retrieve.transaction.by.y.report";
        String ERR_NO_TRANSACTIONS_FOUND ="err.retrieve.no.transaction.found";
        String EX_RETRIEVE_TRANSACTION_BY_Y_REPORT ="ex.retrieve.transaction.by.y.report";
        String ERR_SAVE_SALES_REPORT = "err.retrieve.save.sales.report";
        String EX_SAVE_SALES_REPORT = "ex.retrieve.save.sales.report";
        String ERR_NO_X_REPORTS_FOUND = "err.no.x.reports.found";
        String EX_GET_X_REPORTS = "ex.get.x.reports";
        String ERR_NO_Z_REPORTS_FOUND = "err.no.z.reports.found";
        String EX_GET_Z_REPORTS = "ex.get.z.reports";
		String ERR_SAVE_STAFF_LEAVE_DETAILS = "err.save.staff.leave.details";
		String EX_SAVE_STAFF_LEAVE_DETAILS = "ex.save.staff.leave.details";
		String ERR_UPDATE_STAFF_LEAVE_DETAILS = "err.update.staff.leave.details";
		String EX_UPDATE_STAFF_LEAVE_DETAILS = "ex.update.staff.leave.details";
		String ERR_UPDATE_STAFF_LEAVE_STATUS = "err.update.status.staff.leave.details";
		String EX_UPDATE_STAFF_LEAVE_STATUS = "ex.update.status.staff.leave.details";
		String ERR_RETRIEVE_ALL_STAFF_LEAVE_DETAILS = "err.get.all.staff.leave.details";
		String EX_RETRIEVE_ALL_STAFF_LEAVE_DETAILS = "ex.get.all.staff.leave.details";
		
	}
}
