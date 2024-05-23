//package com.example.app_calculator;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.TextView;
//import androidx.appcompat.app.AppCompatActivity;
//
//public class MainActivity extends AppCompatActivity {
//    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, bdot, bpi, bequal,
//            bplus, bmin, bmul, bdiv, binv, bsqrt, bsquare, bfact, bln, blog,
//            btan, bcos, bsin, bb1, bb2, bc, bac, bhex, bdec, boct, bbin;
//    TextView tvmain, tvsec;
//    String pi = "3.14159265";
//    private RadioGroup modeSelection;
//    private LinearLayout scientificLayout, programLayout;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main); // Ensure this layout contains your RadioGroup
//
//        // Initialize buttons and text views
//        b1 = findViewById(R.id.b1);
//        b2 = findViewById(R.id.b2);
//        b3 = findViewById(R.id.b3);
//        b4 = findViewById(R.id.b4);
//        b5 = findViewById(R.id.b5);
//        b6 = findViewById(R.id.b6);
//        b7 = findViewById(R.id.b7);
//        b8 = findViewById(R.id.b8);
//        b9 = findViewById(R.id.b9);
//        b0 = findViewById(R.id.b0);
//        bpi = findViewById(R.id.bpi);
//        bdot = findViewById(R.id.bdot);
//        bequal = findViewById(R.id.bequal);
//        bplus = findViewById(R.id.bplus);
//        bmin = findViewById(R.id.bmin);
//        bmul = findViewById(R.id.bmul);
//        bdiv = findViewById(R.id.bdiv);
//        binv = findViewById(R.id.binv);
//        bsqrt = findViewById(R.id.bsqrt);
//        bsquare = findViewById(R.id.bsquare);
//        bfact = findViewById(R.id.bfact);
//        bln = findViewById(R.id.bln);
//        blog = findViewById(R.id.blog);
//        btan = findViewById(R.id.btan);
//        bsin = findViewById(R.id.bsin);
//        bcos = findViewById(R.id.bcos);
//        bc = findViewById(R.id.bc);
//        bac = findViewById(R.id.bac);
//        bhex = findViewById(R.id.bhex);
//        bdec = findViewById(R.id.bdec);
//        boct = findViewById(R.id.boct);
//        bbin = findViewById(R.id.bbin);
//
//        tvmain = findViewById(R.id.tvmain);
//        tvsec = findViewById(R.id.tvsec);
//
//        // Initialize the RadioGroup and LinearLayouts
//        modeSelection = findViewById(R.id.modeSelection);
//        scientificLayout = findViewById(R.id.scientificLayout);
//        programLayout = findViewById(R.id.programLayout);
//
//        // Set the default visibility
//        scientificLayout.setVisibility(View.GONE);
//        programLayout.setVisibility(View.GONE);
//
//        // Set a listener for the RadioGroup
//        modeSelection.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                // Update visibility based on the selected radio button
//                if (checkedId == R.id.basicMode) {
//                    scientificLayout.setVisibility(View.GONE);
//                    programLayout.setVisibility(View.GONE);
//                } else if (checkedId == R.id.standardMode) {
//                    scientificLayout.setVisibility(View.GONE);
//                    programLayout.setVisibility(View.VISIBLE);
//                } else if (checkedId == R.id.scientificMode) {
//                    scientificLayout.setVisibility(View.VISIBLE);
//                    programLayout.setVisibility(View.GONE);
//                }
//            }
//        });
//
//        // Optional: Perform actions based on the initial checked state
//        int checkedRadioButtonId = modeSelection.getCheckedRadioButtonId();
//        if (checkedRadioButtonId != -1) {
//            modeSelection.check(checkedRadioButtonId);
//        }
//
//        // Set onclick listeners for buttons
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tvmain.setText(tvmain.getText() + "1");
//            }
//        });
//
//        b2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tvmain.setText(tvmain.getText() + "2");
//            }
//        });
//
//        // Repeat for other number buttons and operations...
//
//        // Clear button
//        bac.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tvmain.setText("");
//                tvsec.setText("");
//            }
//        });
//
//        // Delete button
//        bc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String val = tvmain.getText().toString();
//                val = val.substring(0, val.length() - 1);
//                tvmain.setText(val);
//            }
//        });
//
//        // Equal button
//        bequal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String val = tvmain.getText().toString();
//                String replacedstr = val.replace('÷', '/').replace('×', '*');
//                double result = eval(replacedstr);
//                tvmain.setText(String.valueOf(result));
//                tvsec.setText(val);
//            }
//        });
//    }
//
//    // Factorial function
//    int factorial(int n) {
//        return (n == 1 || n == 0) ? 1 : n * factorial(n - 1);
//    }
//
//    // Eval function
//    public static double eval(final String str) {
//        return new Object() {
//            int pos = -1, ch;
//
//            void nextChar() {
//                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
//            }
//
//            boolean eat(int charToEat) {
//                while (ch == ' ') nextChar();
//                if (ch == charToEat) {
//                    nextChar();
//                    return true;
//                }
//                return false;
//            }
//
//            double parse() {
//                nextChar();
//                double x = parseExpression();
//                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
//                return x;
//            }
//
//            // Grammar:
//            // expression = term | expression `+` term | expression `-` term
//            // term = factor | term `*` factor | term `/` factor
//            // factor = `+` factor | `-` factor | `(` expression `)`
//            //        | number | functionName factor | factor `^` factor
//
//            double parseExpression() {
//                double x = parseTerm();
//                for (;;) {
//                    if      (eat('+')) x += parseTerm(); // addition
//                    else if (eat('-')) x -= parseTerm(); // subtraction
//                    else return x;
//                }
//            }
//
//            double parseTerm() {
//                double x = parseFactor();
//                for (;;) {
//                    if      (eat('*')) x *= parseFactor(); // multiplication
//                    else if (eat('/')) x /= parseFactor(); // division
//                    else return x;
//                }
//            }
//
//            double parseFactor() {
//                if (eat('+')) return parseFactor(); // unary plus
//                if (eat('-')) return -parseFactor(); // unary minus
//
//                double x;
//                int startPos = this.pos;
//                if (eat('(')) { // parentheses
//                    x = parseExpression();
//                    eat(')');
//                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
//                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
//                    x = Double.parseDouble(str.substring(startPos, this.pos));
//                } else if (ch >= 'a' && ch <= 'z') { // functions
//                    while (ch >= 'a' && ch <= 'z') nextChar();
//                    String func = str.substring(startPos, this.pos);
//                    x = parseFactor();
//                    if (func.equals("sqrt")) x = Math.sqrt(x);
//                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
//                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
//                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
//                    else if (func.equals("log")) x = Math.log10(x);
//                    else if (func.equals("ln")) x = Math.log(x);
//                    else throw new RuntimeException("Unknown function: " + func);
//                } else {
//                    throw new RuntimeException("Unexpected: " + (char)ch);
//                }
//
//                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation
//
//                return x;
//            }
//        }.parse();
//    }
//}
