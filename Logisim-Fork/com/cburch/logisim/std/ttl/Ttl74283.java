package com.cburch.logisim.std.ttl;

import java.awt.Graphics;

import com.cburch.logisim.data.BitWidth;
import com.cburch.logisim.data.Value;
import com.cburch.logisim.instance.InstancePainter;
import com.cburch.logisim.instance.InstanceState;
import com.cburch.logisim.util.GraphicsUtil;

public class Ttl74283 extends AbstractTtlGate {
	protected final static String[] Ttl74283portnames = { "∑2", "B2", "A2", "∑1", "A1", "B1", "CIN", "C4", "∑4", "B4",
			"A4", "∑3", "A3", "B3" };

	public Ttl74283() {
		super("74283", 16, new int[] { 1, 4, 9, 10, 13 }, Ttl74283portnames);
	}

	@Override
	public void paintInternal(InstancePainter painter, int x, int y, int height, boolean up) {
		Graphics g = painter.getGraphics();
		g.drawRect(x + 10, y + AbstractTtlGate.pinheight + 10, super.pinnumber * 10 - 20,
				height - 2 * AbstractTtlGate.pinheight - 20);
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 7; j++) {
				GraphicsUtil.drawCenteredText(g, Ttl74283portnames[j + (i * 7)],
						i == 0 ? x + 10 + j * 20 : x + 160 - j * 20 - 10,
						y + height - AbstractTtlGate.pinheight - 7 - i * (height - 2 * AbstractTtlGate.pinheight - 11));
			}
		}
	}

	@Override
	public void ttlpropagate(InstanceState state) {
		byte A1 = state.getPort(4) == Value.TRUE ? (byte) 1 : 0;
		byte A2 = state.getPort(2) == Value.TRUE ? (byte) 2 : 0;
		byte A3 = state.getPort(12) == Value.TRUE ? (byte) 4 : 0;
		byte A4 = state.getPort(10) == Value.TRUE ? (byte) 8 : 0;
		byte B1 = state.getPort(5) == Value.TRUE ? (byte) 1 : 0;
		byte B2 = state.getPort(1) == Value.TRUE ? (byte) 2 : 0;
		byte B3 = state.getPort(13) == Value.TRUE ? (byte) 4 : 0;
		byte B4 = state.getPort(9) == Value.TRUE ? (byte) 8 : 0;
		byte CIN = state.getPort(6) == Value.TRUE ? (byte) 1 : 0;
		byte sum = (byte) (A1 + A2 + A3 + A4 + B1 + B2 + B3 + B4 + CIN);
		Value output = Value.createKnown(BitWidth.create(5), sum);
		state.setPort(3, output.get(0), 1);
		state.setPort(0, output.get(1), 1);
		state.setPort(11, output.get(2), 1);
		state.setPort(8, output.get(3), 1);
		state.setPort(7, output.get(4), 1);
	}
}
