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


