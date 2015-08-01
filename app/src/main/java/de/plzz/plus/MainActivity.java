package de.plzz.plus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    static private boolean editing = true;
    static private final List<BigDecimal> stack = new ArrayList<>();
    static private BigDecimal store = new BigDecimal("0");

    private void push() {
        TextView displayEditText = (TextView) findViewById(R.id.displayEditText1);
        String text = displayEditText.getText().toString();
        BigDecimal n;
        try {
            n = new BigDecimal(text.length() > 0 ? text : "0");
            stack.add(0, n);
            editing = false;
        } catch (NumberFormatException e) {}
    }

    private void show() {
        String display3 = stack.size() > 2 ? stack.get(2).toString() : "";
        TextView displayEditText3 = (TextView) findViewById(R.id.displayEditText3);
        displayEditText3.setText(display3);
        String display2 = stack.size() > 1 ? stack.get(1).toString() : "";
        TextView displayEditText2 = (TextView) findViewById(R.id.displayEditText2);
        displayEditText2.setText(display2);
        String display1 = stack.size() > 0 ? stack.get(0).toString() : "0";
        TextView displayEditText1 = (TextView) findViewById(R.id.displayEditText1);
        displayEditText1.setText(display1);
    }

    public void onButtonAddClick(View view) {
        if (editing) {
            push();
        }
        if (stack.size() >= 2) {
            BigDecimal y = stack.remove(0);
            BigDecimal x = stack.remove(0);
            stack.add(0, x.add(y));
        }
        show();
    }

    public void onButtonSubtractClick(View view) {
        if (editing) {
            push();
        }
        if (stack.size() >= 2) {
            BigDecimal y = stack.remove(0);
            BigDecimal x = stack.remove(0);
            stack.add(0, x.subtract(y));
        }
        show();
    }

    public void onButtonMultiplyClick(View view) {
        if (editing) {
            push();
        }
        if (stack.size() >= 2) {
            BigDecimal y = stack.remove(0);
            BigDecimal x = stack.remove(0);
            stack.add(0, x.multiply(y));
        }
        show();
    }

    public void onButtonDivideClick(View view) {
        if (editing) {
            push();
        }
        if (stack.size() >= 2) {
            BigDecimal y = stack.remove(0);
            BigDecimal x = stack.remove(0);
            try {
                stack.add(0, x.divide(y, MathContext.DECIMAL64));
            } catch (ArithmeticException e) {
                stack.add(0, x);
                stack.add(0, y);
            }
        }
        show();
    }

    public void onButtonSwapClick(View view) {
        if (editing) {
            push();
        }
        if (stack.size() >= 2) {
            BigDecimal x = stack.remove(0);
            BigDecimal y = stack.remove(0);
            stack.add(0, x);
            stack.add(0, y);
        }
        show();
    }

    public void onButtonDownClick(View view) {
        if (editing) {
            push();
        }
        if (stack.size() >= 2) {
            BigDecimal x = stack.remove(0);
            stack.add(x);
        }
        show();
    }

    public void onButtonNegateClick(View view) {
        if (editing) {
            push();
        }
        if (stack.size() >= 1) {
            BigDecimal x = stack.remove(0);
            stack.add(0, x.negate());
        }
        show();
    }

    public void onButtonStoreClick(View view) {
        if (editing) {
            push();
        }
        store = stack.get(0);
        show();
    }

    public void onButtonRecallClick(View view) {
        if (editing) {
            push();
        }
        stack.add(0, store);
        show();
    }

    public void onButtonEnterClick(View view) {
        if (editing) {
            push();
        }
        show();
    }

    public void onButtonDeleteClick(View view) {
        TextView displayEditText = (TextView) findViewById(R.id.displayEditText1);
        CharSequence text = displayEditText.getText().toString();
        if (editing && text.length() > 1) {
            displayEditText.setText(text.subSequence(0, text.length() - 1));
        } else if (editing) {
            displayEditText.setText("0");
        } else if (stack.size() >= 2) {
            stack.remove(0);
            show();
        } else if (stack.size() == 1) {
            stack.remove(0);
            show();
        }
    }

    private void addDigit(String digit) {
        TextView displayEditText = (TextView) findViewById(R.id.displayEditText1);
        if (!editing) {
            displayEditText.setText("");
        }
        String text = displayEditText.getText().toString();
        BigDecimal n = new BigDecimal(text + digit);
        displayEditText.setText(n.toString());
        editing = true;
    }

    public void onButton7Click(View view) {
        addDigit("7");
    }

    public void onButton8Click(View view) {
        addDigit("8");
    }

    public void onButton9Click(View view) {
        addDigit("9");
    }

    public void onButton4Click(View view) {
        addDigit("4");
    }

    public void onButton5Click(View view) {
        addDigit("5");
    }

    public void onButton6Click(View view) {
        addDigit("6");
    }

    public void onButton1Click(View view) {
        addDigit("1");
    }

    public void onButton2Click(View view) {
        addDigit("2");
    }

    public void onButton3Click(View view) {
        addDigit("3");
    }

    public void onButton0Click(View view) {
        addDigit("0");
    }

    public void onButtonDotClick(View view) {
        TextView displayEditText = (TextView) findViewById(R.id.displayEditText1);
        if (!editing) {
            displayEditText.setText("");
        }
        String text = displayEditText.getText().toString();
        if (!text.contains(".")) {
            displayEditText.setText(text + ".");
        }
        editing = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
            MainActivity.this.startActivity(aboutIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
