
function checkin(userid, control){
    if(userid && control){
      var now = new Date();
      SpreadsheetApp.getActiveSpreadsheet().getSheetByName("Registros de VC Laminations 2").appendRow([userid, control, now]);
      return now.toLocaleString();
    }
  
  
  }
  
  function doGet(e){
    var tpl = HtmlService.createTemplateFromFile("page.html");
    tpl.data = e.parameters;
    tpl.data.id = SpreadsheetApp.getActiveSpreadsheet().getId();
    return tpl.evaluate();
  
  }
  