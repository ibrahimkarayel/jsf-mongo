/**
 * Created by ikarayel on 4/16/14.
 */
function handleAddRequest(xhr, status, args) {
    if (args.validationFailed) {
        PF('addDialog').jq.effect("shake", { times: 5 }, 100);
        Recaptcha.reload();
    }
    else {
        $('#dialogForm').each(function () {
            this.reset();
        });
        Recaptcha.reload();
        PF('addDialog').hide();
    }
}

function handleUpdateRequest(xhr, status, args) {
    $('#updateDialogForm').each(function () {
        this.reset();
    });
    PF('updateDialog').hide();

}

function show_confirm() {
    var c = window.confirm('Are you sure you want to delete it?');
    if (c)
        return true; //this means that they clicked 'OK'
    else
        return false;//this means they clicked 'Cancel'
}