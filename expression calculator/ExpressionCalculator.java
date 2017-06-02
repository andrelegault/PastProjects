/**
 * Expression calculator based implementing PEMDAS (Parenthesis. exponant, multiplication, division, addition and substraction).
 * Requires sanitized user input and valid mathematical expressions to function.
 *
 * @author André Parsons-Legault
 */

public class ExpressionCalculator {

	private String expression; // variable to hold the expression submitted by the user
	private int result; // variable that holds the result
	private MyStack<Integer> iStack; // integer stack
	private MyStack<Character> oStack; // operator stack

	/**
	 * Default constructor.
	 * Instantiates empty data members.
	 */
	public ExpressionCalculator() {
		expression = "";
		result = 0;
		iStack = new MyStack<Integer>();
		oStack = new MyStack<Character>();
	}

	/**
	 * Sets the expression to the user's.
	 *
	 * @param expression expression given by the user.
	 */
	public void setExpression(String expression) {
		this.expression = expression + " ";
	}

	/**
	 * Evaluates the expression by doing the required depending on the character. Goes through every character.
	 * If the character is an integer it is added to the integer stack. If it's an operator, based on its priority it is.....
	 */
	public void evaluate() {
		int index = 0;
		while(index < expression.length()) {
			char ch = expression.charAt(index);
			
			if(Character.isDigit(ch)) {
				String temp = "";
				while(Character.isDigit(expression.charAt(index))) {
					temp += expression.charAt(index);
					index++;
				}
				iStack.push(Integer.parseInt(temp));
			}
			else if(isOperator(ch)) {
				if(oStack.isEmpty() || priority(oStack.lookUp(), ch) == 1) {
					oStack.push(ch);
					index++;
				}
				else calculate();
			}
			else if(ch == '(') {
				oStack.push(ch);
				index++;
			}
			else if(ch == ')') {
				while(oStack.lookUp() != '(') {
					calculate();
				}
				oStack.pop();
				index++;
			}
			else index++;
		}
		while(!oStack.isEmpty()) calculate();
		result = iStack.pop();
	}

	/**
	 * Takes two integers from the integer stack and an operator from the associated stack to do a calculation based on the operator.
	 * Next, inserts the integer given by the calculation into the integer stack.
	 */
	public void calculate() {
		int x2 = iStack.pop(), x1 = iStack.pop(), result = 0;
		char ch = oStack.pop();
		switch(ch) {
			case '+': result = x1 + x2; break;
			case '-': result = x1 - x2; break;
			case '*': result = x1 * x2; break;
			case '/': result = x1 / x2; break;
			case '%': result = x1 % x2; break;
			case '^': result = (int)(Math.pow(x1, x2)); break;
			default:break;
		}
		iStack.push(result);
	}

	/**
	 * Checks the priority of the given operator based on PEMDAS compared to the one on top of the oprator stack.
	 *
	 * @return -1 if the operator currently in the stack is of higher priority.
	 * @return 0 if the operators are of equal priority.
	 * @return 1 if the operator in the stack is of lower priority.
	 */
	private int priority(char look, char ch) {
		if(oStack.isEmpty()) return 1;
		else {
			int p1 = getPriority(look);
			int p2 = getPriority(ch);
			if(p2 == p1) return 0;
			else if(p2 < p1) return -1;
			else return 1;
		}
	}

	/**
	 * Returns the priority of said character.
	 * 
	 * @param ch character that is to be checked
	 * @return priority of parameter
	 */
	private int getPriority(char ch) {
		if(ch == '(') return 0;
		else if(ch == '^') return 3;
		else if(ch == '*' || ch == '/' || ch == '%') return 2;
		else return 1;
	}
	
	/**
	 * Checks whether or not the given character is an operator.
	 * @return True if the character is an operator.
	 */
	private boolean isOperator(char ch) {
		switch ( ch ) {
			case '^': case '%': case '*': case '/': case '+': case '-': return true;
			default: return false;
		}
	}

	/**
	 * toString method.
	 */
	public String toString() {
		return expression + " = " + result;
	}
	
	public static void main(String[] args) {
		ExpressionCalculator calc = new ExpressionCalculator();
		calc.setExpression("30 - 2 + (20 - 19 * 1000)");
		calc.evaluate();
		int res = 30 - 2 + (20 - 19 * 1000);
        if(calc.result == res)
            System.out.println(calc.result + " = " + res);
        else
            System.out.println(calc.result + " != " + res);
	}
}