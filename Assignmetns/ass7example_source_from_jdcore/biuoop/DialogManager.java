package biuoop;

public abstract interface DialogManager
{
  public abstract String showQuestionDialog(String paramString1, String paramString2, String paramString3);
  
  public abstract void showInformationDialog(String paramString1, String paramString2);
  
  public abstract void showWarningDialog(String paramString1, String paramString2);
  
  public abstract void showErrorDialog(String paramString1, String paramString2);
  
  public abstract boolean showConfirmationDialog(String paramString1, String paramString2);
  
  public abstract boolean showYesNoDialog(String paramString1, String paramString2);
}
