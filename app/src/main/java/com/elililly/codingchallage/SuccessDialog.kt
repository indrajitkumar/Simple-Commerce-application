package com.elililly.codingchallage

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

class SuccessDialog(context: Context) : AlertDialog.Builder(context) {
    private var dialogListner: DialogListner? = null
    fun show(title: String, message: String, orderId: String) {
        val dialogBuilder = AlertDialog.Builder(context)

        dialogBuilder.setMessage("Thank you, Order has been successfully placed with order id: $orderId")
            .setCancelable(false)
            .setNegativeButton("Dismiss", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
                dialogListner?.onDialogDismiss()
            })

        val alert = dialogBuilder.create()
        alert.setTitle("Congratulation")
        alert.show()
    }

    fun setDialogListner(dialogListner: DialogListner) {
        this.dialogListner = dialogListner
    }
}