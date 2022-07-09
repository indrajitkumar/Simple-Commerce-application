package com.elililly.codingchallage

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

class SuccessDialog(context: Context) : AlertDialog.Builder(context) {
    private var dialogListener: DialogListner? = null
    fun show(name: String, paymentMethod: String, orderId: String) {
        val dialogBuilder = AlertDialog.Builder(context)
        val formattedString =
            String.format(context.getString(R.string.successOrderMsg), name, orderId, paymentMethod)
        dialogBuilder.setMessage(
            formattedString
        )
            .setCancelable(false)
            .setNegativeButton("Dismiss") { dialog, id ->
                dialog.cancel()
                dialogListener?.onDialogDismiss()
            }

        val alert = dialogBuilder.create()
        alert.setTitle("Congratulation")
        alert.show()
    }

    fun setDialogListener(dialogListener: DialogListner) {
        this.dialogListener = dialogListener
    }
}