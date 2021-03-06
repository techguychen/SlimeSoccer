package network;

import java.io.IOException;

import javax.swing.JFrame;

import view.MainMenuGuest;
import view.MainMenuUser;

public class ClientThread extends Thread {
	private MainMenuGuest mmg = null;
	private MainMenuUser mmu = null;
	private boolean isGuest = false;

	public ClientThread(JFrame mm, boolean isGuest) {
		this.isGuest = isGuest;
		if (isGuest) mmg = (MainMenuGuest) mm;
		else mmu = (MainMenuUser) mm;
	}
	
	public void run() {
		while (true) {
			try {
				String str;
				if (isGuest) { // this one uses mmg
					str = mmg.sReader.readLine();
					if (str == null) break;
					// str.charAt(0) is an identifier we add which tells us what to do with the string
					if (str.charAt(0) == 'C') { // chat
						mmg.chatArea.setText(mmg.chatArea.getText() + "\n" + str.substring(1));
					}
				} else { // this one uses mmu
					str = mmu.sReader.readLine();
					if (str == null) break;
					if (str.charAt(0) == 'C') { // chat
						mmu.chatArea.setText(mmu.chatArea.getText() + "\n" + str.substring(1));
					} else if (str.charAt(0) == 'Y') {
						if (str.charAt(1) == '1') {
							mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.isPlayer1 = true;
							mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p1Username = mmu.getUsername();
							mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p1UsernameLabel.setText(mmu.getUsername());
							mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p2Username = str.substring(2);
							mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p2UsernameLabel.setText("<html><div style=\"text-align: center;\">" + str.substring(2));
						}
						else {
							mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.isPlayer1 = false;
							mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p2Username = mmu.getUsername();
							mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p2UsernameLabel.setText(mmu.getUsername());
							mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p1Username = str.substring(2);
							mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p1UsernameLabel.setText("<html><div style=\"text-align: center;\">" + str.substring(2));
						}
						mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.setVisible(true);
						mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.setVisible(false);
					}
				}
			} catch (IOException ioe) {
				System.out.println("IOException in ClientThread.run(): " + ioe.getMessage());
			}
		}
	}
}
