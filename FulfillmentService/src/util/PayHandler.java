package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bank.BankDAO;
import bank.BankDTO;
import order.OrderDAO;
import order.OrderDTO;
import pay.PayDAO;
import pay.PayDTO;
import state.AdminName;
import state.PayState;
import storage.SoldProductDAO;
import storage.SoldProductDTO;

public class PayHandler {
	private static final Logger LOG = LoggerFactory.getLogger(PayHandler.class);
	DateHandler dc = new DateHandler();
	PayDAO pDao = new PayDAO();
	PayDTO pay = new PayDTO();
	BankDTO bank = new BankDTO();
	BankDAO bDao = new BankDAO();
	OrderDAO oDao = new OrderDAO();
	OrderDTO order = new OrderDTO();
	SoldProductDAO spDao = new SoldProductDAO();
	SoldProductDTO sold = new SoldProductDTO();
	BankAccount account = new BankAccount();
	
	// 지급 가능시간 여부 판단 메소드
	public boolean isPossiblePayByDate(String date) {
		String strDate = dc.getPayTime(date.substring(0, 7));
		if(date.substring(0, 7).compareTo(strDate) >= 0) return false;
		else return true;
	}
	
	// 구매처 지급
	public void processPayForSupplier(String oBankId, int oAdminId, int total, String oDate) {
		if(isPossiblePayByDate(oDate)) {
			pay = new PayDTO(oBankId, oAdminId, total, dc.currentTime(), String.valueOf(PayState.지급));
			pDao.addPayList(pay);
			oDao.updatePayState(String.valueOf(PayState.지급), oDate.substring(0, 7));
			bank = bDao.getOneBankList(oBankId);
			bDao.updateBank(bank, total, "+");
			bank = bDao.getOneBankList(account.getAccount(String.valueOf(AdminName.ezen)));
			bDao.updateBank(bank, total, "-");
		}
	}
	
	// 운송회사 지급
	public void processPayForTransport(String soldBankId, int soldTransportId, int total, String soldDate, String soldInvId) {
		if(isPossiblePayByDate(soldDate)) {
			pay = new PayDTO(soldBankId, soldTransportId, total, dc.currentTime(), String.valueOf(PayState.지급));
			pDao.addPayList(pay);
			spDao.updateTransportState(String.valueOf(PayState.지급), soldInvId);
			bank = bDao.getOneBankList(soldBankId);
			bDao.updateBank(bank, total, "+");
			bank = bDao.getOneBankList(account.getAccount(String.valueOf(AdminName.ezen)));
			bDao.updateBank(bank, total, "-");
		}
	}
}
