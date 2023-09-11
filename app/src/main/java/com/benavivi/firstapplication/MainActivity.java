package com.benavivi.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	private double num1, num2;
	private TextView result;
	private EditText textNum1, textNum2;
	private RadioButton selectedRadioButton;


	/**
	 * Returns a boolean value whether or not the input numbers(textNum1 , TextNum2) are valid.
	 */
	public boolean isInputValid () {

		String stringFirstNum = this.textNum1.getText().toString();
		String stringSecondNum = this.textNum2.getText().toString();

		if (stringFirstNum.isEmpty() || stringSecondNum.isEmpty()) {
			Toast.makeText(MainActivity.this, "ERROR: Invalid enter, please enter both numbers.", Toast.LENGTH_LONG).show();
			return false;
		}
		return true;
	}


	//Returns a boolean value whether or not is successfully updated both numbers.
	public boolean updateNumberValues () {
		if (isInputValid()) {
			this.num1 = Double.parseDouble(this.textNum1.getText().toString());
			this.num2 = Double.parseDouble(this.textNum2.getText().toString());
			return true;
		}
		return false;
	}

	public void onClickChooseAdd (View v) {
		performCalculation('+');
	}

	public void onClickChooseSub (View v) {
		performCalculation('-');
	}

	public void onClickChooseMul (View v) {
		performCalculation('*');
	}

	public void onClickChooseDiv (View v) {
		performCalculation('/');
	}

	/**
	 * @param operation A char value containing the operation that is going to be carried out.
	 */
	public void performCalculation (char operation) {
		String reply;
		double calculation;
		if (!updateNumberValues()) return;


		//Handle special cases - aka, division by 0.
		if (operation == '/' && num2 == 0) {
			reply = "Division by zero is Undefined.";
			result.setText(reply);
			return; //Exit calculation
		}

		reply = "Calculation: " + num1 + " " + operation + " " + num2 + " = ";

		switch (operation) {
			case '+':
				calculation = num1 + num2;
				break;
			case '-':
				calculation = num1 - num2;
				break;
			case '*':
				calculation = num1 * num2;
				break;
			case '/':
				calculation = num1 / num2;
				break;
			default:
				Toast.makeText(MainActivity.this, "An error has occurred, please try again", Toast.LENGTH_SHORT).show();
				return; //Exit func
		}

		reply += calculation;
		result.setText(reply);
	}

	//This function is used as the lisenter for the "Calculate" button.
	public void onClickListenerCalculation () {
		RadioGroup radioOperationsGroup = findViewById(R.id.operationsRadioGroup);
		Button calculateButton = findViewById(R.id.calculateButton);


		calculateButton.setOnClickListener(view -> {
			int selectedRadioID = radioOperationsGroup.getCheckedRadioButtonId();
			if (selectedRadioID == -1)
				Toast.makeText(MainActivity.this, "Please select a Radio button operation", Toast.LENGTH_LONG).show();
			else {
				this.selectedRadioButton = findViewById(selectedRadioID);
				char operation = selectedRadioButton.getText().toString().charAt(1); //Radio button follow this format: (OPERATION) NAME
				performCalculation(operation);
			}
		});
	}


	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textNum1 = findViewById(R.id.firstNumber);
		textNum2 = findViewById(R.id.secondNumber);
		result = findViewById(R.id.resultText);

		onClickListenerCalculation();

	}

}