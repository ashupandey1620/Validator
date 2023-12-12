package com.original.sense.psit

//    private fun handleTechTag(intent: Intent) {
//        val tag: Tag? = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
//        val nfca = NfcA.get(tag)
//        if (nfca != null) {
//            try {
//                nfca.connect()
//
//                Toast.makeText(this,"Connected",Toast.LENGTH_SHORT).show()
//
//                // Read NFC-A tag data
//                val tagData = nfca.tag.id
//                if (tagData != null && tagData.size > 0) {
//                    val tagId: String = byteArrayToHexString(tagData)
//                    Toast.makeText(this,"$tagId",Toast.LENGTH_SHORT).show()
//                    Toast.makeText(this,"$tagData",Toast.LENGTH_SHORT).show()
//                }
//            } catch (e: IOException) {
//                e.printStackTrace()
//                Toast.makeText(this , "Error reading NFC-A tag" , Toast.LENGTH_SHORT).show()
//            } finally {
//                try {
//                    nfca.close()
//                } catch (e: IOException) {
//                    e.printStackTrace()
//                }
//            }
//        }
//    }
//
//
//    private fun enableNfcForegroundDispatch() {
//        val intent = Intent(this , MainActivity::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
//        nfcAdapter?.enableForegroundDispatch(
//            this ,
//            PendingIntent.getActivity(
//                this ,
//                0 ,
//                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP) ,
//                PendingIntent.FLAG_MUTABLE
//            ) ,
//            null ,
//            null
//        )
//    }
//
//
//    private fun disableNfcForegroundDispatch() {
//        nfcAdapter?.disableForegroundDispatch(this)
//    }
//
//    private fun byteArrayToHexString(bytes: ByteArray): String {
//        val sb = StringBuilder()
//        for (b in bytes) {
//            sb.append(String.format("%02x" , b))
//        }
//        return sb.toString()
//    }







//    override fun onResume() {
//        super.onResume()
//        enableNfcForegroundDispatch()
//    }
//    override fun onPause() {
//        super.onPause()
//        disableNfcForegroundDispatch()
//    }
//

// if (password != null) {
//    // Handle the received password here
//    // Toast.makeText(MainActivity.this, "Password: " + password, Toast.LENGTH_SHORT).show();
//    val a = password.substring(0 , 2).toInt(16) as Byte
//    val b = password.substring(2 , 4).toInt(16) as Byte
//    val c = password.substring(4 , 6).toInt(16) as Byte
//    val d = password.substring(6 , 8).toInt(16) as Byte
//    val pwd = byteArrayOf(a , b , c , d)
//    val authCommand = byteArrayOf(
//        0x1B.toByte() ,  // PWD_AUTH command
//        pwd[0] , pwd[1] , pwd[2] , pwd[3]
//    )
//    try {
//        nfca.connect()
//        //  Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
//        val result: ByteArray = nfca.transceive(authCommand)
//        if (result != null && result.size >= 2 && result[0] == 0x00.toByte() && result[1] == 0x00.toByte()) {
//            // Authentication successful
//            val PUIDData: ByteArray = nfca.transceive(byteArrayOf(0x30.toByte() , 0x04.toByte()))
//            var PUIDValue: String = byteArrayToHexString(PUIDData)
//            //Toast.makeText(MainActivity.this, "NFC-A Tag Value: " + PUIDValue, Toast.LENGTH_SHORT).show();
////testing
//            PUIDValue = PUIDValue.substring(0 , 12).uppercase(Locale.getDefault())
//            sendPuidToServer(PUIDValue)
//        } else {
//            Toast.makeText(this@MainActivity , "Tag Data is null" , Toast.LENGTH_SHORT).show()
//        }
//    } catch (e: IOException) {
//        e.printStackTrace()
//        dialog.cancel()
//        dialogTrans.show()
//        // Toast.makeText(MainActivity.this, "Error transceiving data", Toast.LENGTH_SHORT).show();
//    }
//}
