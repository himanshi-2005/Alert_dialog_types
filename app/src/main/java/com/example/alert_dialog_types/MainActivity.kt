package com.example.alert_dialog_types

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.alert_dialog_types.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var mylist= arrayOf("1","2","3","4")
    var checklist= booleanArrayOf(true,false,false,true)
    var simpleDateFormat =SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btn1.setOnClickListener{
            Toast.makeText(this,"This is a toast message",Toast.LENGTH_SHORT).show()
        }
        binding.btn2.setOnClickListener{
            Snackbar.make(it,"This is a Snackbar message",Snackbar.LENGTH_SHORT).setAction(
                "Undo",{Toast.makeText(this,"Toast invoked  by snackbar",Toast.LENGTH_SHORT).show()
                }
            ).show()
        }
        binding.btn3.setOnClickListener{
          AlertDialog.Builder(this) .apply{
              setTitle("Do you want to exit this screen")
              setPositiveButton("Yes"){_,_->
                  finish()
                  Toast.makeText(this@MainActivity,"Positive",Toast.LENGTH_SHORT).show()
              }
              setNegativeButton("No"){_,_->
                 Toast.makeText(this@MainActivity,"Negative",Toast.LENGTH_SHORT).show()
              }
              setCancelable(false)
              show()
          }
        }
        binding.btn4.setOnClickListener{
            AlertDialog.Builder(this@MainActivity).apply {
              setTitle("My list")
                setItems(mylist){_,index->
                  Toast.makeText(this@MainActivity,mylist[index],Toast.LENGTH_SHORT).show()
                }
                setNegativeButton("No"){_,_->
                }
                setPositiveButton("Yes"){_,_,->
                }
                setNeutralButton("Cancel"){_,_->
                }
                setCancelable(false)
                show()
            }
        }
        binding.btn5.setOnClickListener{
            AlertDialog.Builder(this).apply {
                setTitle("This is a Alert Dialog")
                setMultiChoiceItems(mylist,checklist,{_,which,isChecked->
                   checklist.set(which,isChecked)
                Toast.makeText(this@MainActivity,mylist[which].toString(),Toast.LENGTH_SHORT).show()
            })
            setPositiveButton("Yes"){_,_,->
            }
            setNegativeButton("No"){_,_,->
            }
            setCancelable(true)
            show()
            }
        }

        binding.btn6.setOnClickListener {
            var dialog = Dialog(this)
            dialog.setContentView(R.layout.custom)
            var btn=dialog.findViewById<Button>(R.id.btn1)

            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
            )
            dialog.show()
            btn.setOnClickListener{
                Toast.makeText(this, "btnClick", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            dialog.setCancelable(false)
        }
      binding.btn7.setOnClickListener{
          DatePickerDialog(
              this,
              {_,year,month,dateOfMonth ->
                 Log.e("date","picked Date is $year $month $dateOfMonth")
                  val calendar =Calendar.getInstance()
                  calendar.set(year,month,dateOfMonth)
                  var formattedDate=simpleDateFormat.format(calendar.time)
                  binding.btn7.setText(formattedDate)
              },
              Calendar.getInstance().get(Calendar.YEAR),
              Calendar.getInstance().get(Calendar.MONTH),
              Calendar.getInstance().get(Calendar.DATE)
          ).show()
      }
    }
}






