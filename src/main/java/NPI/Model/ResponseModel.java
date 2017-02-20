package NPI.Model;

public class ResponseModel {

	private String Number;
	private String Status;
	private String StatusCode;
	private String OnTheBasisDocumentType;
	private String OnTheBasisNumber;
	private String RecipientDateTime;
	
	
//	public ResponseModel(String number, String status, String statusCode, String onTheBasisDocumentType,
//			String onTheBasisNumber) {
//		super();
//		Number = number;
//		Status = status;
//		StatusCode = statusCode;
//		OnTheBasisDocumentType = onTheBasisDocumentType;
//		OnTheBasisNumber = onTheBasisNumber;
//	}
	
	public ResponseModel(String number, String status, String statusCode, String onTheBasisDocumentType,
			String onTheBasisNumber, String recipientDateTime) {
		super();
		Number = number;
		Status = status;
		StatusCode = statusCode;
		OnTheBasisDocumentType = onTheBasisDocumentType;
		OnTheBasisNumber = onTheBasisNumber;
		RecipientDateTime = recipientDateTime;
	}
	public String getNumber() {
		return Number;
	}
	public void setNumber(String number) {
		Number = number;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getStatusCode() {
		return StatusCode;
	}
	public void setStatusCode(String statusCode) {
		StatusCode = statusCode;
	}
	public String getOnTheBasisDocumentType() {
		return OnTheBasisDocumentType;
	}
	public void setOnTheBasisDocumentType(String onTheBasisDocumentType) {
		OnTheBasisDocumentType = onTheBasisDocumentType;
	}
	public String getOnTheBasisNumber() {
		return OnTheBasisNumber;
	}
	public void setOnTheBasisNumber(String onTheBasisNumber) {
		OnTheBasisNumber = onTheBasisNumber;
	}
	public String getRecipientDateTime() {
		return RecipientDateTime;
	}
	public void setRecipientDateTime(String recipientDateTime) {
		RecipientDateTime = recipientDateTime;
	}

	
}
