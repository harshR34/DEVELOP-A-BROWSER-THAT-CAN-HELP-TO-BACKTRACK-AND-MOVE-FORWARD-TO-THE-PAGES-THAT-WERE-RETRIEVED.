package Data_Structure;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
/**
 * web class is for simulation of all methods for web browser.
 */
class Web{
    static int count = 0;
    static String green="\u001b[32m";
    static String st = "\u001B[5m";
    static String italic = "\u001B[3m";
    static String rest="\u001b[0m";
    static String red="\u001b[31m";
    static String cyan="\u001b[36m";
    static String yellow ="\u001B[33m";
    static String blue = "\u001B[34m";
    static String bold = "\u001B[1m";
    static String magenta ="\u001B[35m";
    File f;
    FileWriter fw;
    BufferedWriter bw;
    LocalDate date = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
    String myDate = date.format(formatter);

    LocalTime time = LocalTime.now();
    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("hh:mm");
    String myTime = time.format(formatter1);

    class Node{
        String url;
        boolean bookMarked ;
        boolean favourite ;
        Node next;
        boolean lastNode;
        String uniqueSign = "";
        Node(String url){
            this.url = url;
            this.next = null;
            this.lastNode = false;
            this.bookMarked = false;
            this.favourite = false;
        }
    }
    Node head = null;

    /**
     *The addFirst method adds a new node containing a web URL to the beginning of a circular linked list.
     * @param urlWEB A new Node object, p, is created to store the given urlWEB.
     */
    public void addFirst(String urlWEB){
        Node p = new Node(urlWEB);
        if(head==null){
            head = p;
            p.next = head;
        }
        else{
            p.next = head;
            Node last = head;
            while(last.next!=head){
                last = last.next;
            }
            head = p;
            last.next = head;
        }
    }
    /**
     * The addLast method adds a new node containing a web URL to the end of a circular linked list.
     * @param urlWEB A new Node object, p, is created to store the given urlWEB.
     */
    public void addLast(String urlWEB){
        Node p = new Node(urlWEB);
        if(head == null){
            head = p;
            p.next = head;
        }
        else{
            Node last = head;
            while(last.next!=head){
                last = last.next;
            }
            last.next = p;
            p.next = head;
        }
    }

    /**
     * The removeLast method removes the last node from the circular linked list
     * and returns the URL stored in that removed node.
     * @return this returns removed url;
     */
    public String removeLast(){
        if (head == null) {
            return "No tab opened.";
        }
        String removedData;
        if (head.next == null) {
            removedData = head.url;
            head = null;
        } else {
            Node current = head;
            while (current.next.next != head) {
                current = current.next;
            }
            removedData = current.next.url;
            current.next = head;
        }
        return removedData;
    }

    /**
     * The removeFirst method removes the first node from the circular linked list
     * and returns the URL stored in that removed node.
     * @return
     */
    public String removeFirst(){
        if (head == null) {
            return "No tab opened.";
        }
        String removedData = head.url;
        if (head.next == head) {
            head = null;
        } else {
            Node current = head;
            while (current.next != head) {
                current = current.next;
            }
            current.next = head.next;
            head = head.next;
        }

        return removedData;
    }

    /**
     * The open_Tab_before_any_tab method aims to insert a new tab with a given URL
     * immediately before a specified existing tab in the circular linked list.
     * @param justBefore
     * @param NewUrlWeb
     */
    public void open_Tab_before_any_tab(String justBefore,String NewUrlWeb){
        Node p = new Node(NewUrlWeb);
        p.lastNode = false;
        Node temp = head;
        if(head==null){
            System.out.println("Linked list is empty");
        } else if (head.url.equalsIgnoreCase(justBefore)) {
            p.next = head;
            Node last = head;
            while (last.next!=head){
                last = last.next;
            }
            head = p;
            last.next = head;
            p.lastNode = true;
        }
        else{
            int flag = 0;
            do{
                if(temp.url.equalsIgnoreCase(justBefore)){
                    flag=1;
                    break;
                }
                temp = temp.next;
            }while (temp!=head);
            if(flag==0){
                System.out.println(red+bold+"URL doesn't exist!"+rest);
            }
            else{
                temp = head;
                while(!temp.next.url.equalsIgnoreCase(justBefore)){
                    temp = temp.next;
                }
                p.next = temp.next;
                temp.next = p;
                p.lastNode = true;
            }
        }
    }

    /**
     *The close_perticular_tab method aims to close a specific tab
     * with a given URL in the circular linked list.
     * @param urlWEB
     */
    public void close_perticular_tab(String urlWEB){
        Node temp = head;
        if(head == null){
            System.out.println("Linked list is empty.");
        }
        else{
            int flag = 0;
            do{
                if(temp.url.equalsIgnoreCase(urlWEB)){
                    flag=1;
                    break;
                }
                temp = temp.next;
            }while (temp!=head);
            if(flag==0){
                System.out.println(red+bold+"URL not found."+rest);
            }
            else{
                if (head.url.equalsIgnoreCase(urlWEB)) {
                    Node last = head;
                    while(last.next!=head){
                        last = last.next;
                    }
                    head = head.next;
                    last.next = head;
                }
                else{
                    temp = head;
                    while(!temp.next.url.equalsIgnoreCase(urlWEB)){
                        temp = temp.next;
                    }
                    temp.next = temp.next.next;
                }
            }
        }
    }

    /**
     *The open_after_tab method aims to insert a new tab with a given URL immediately
     *  after a specified existing tab in the circular linked list.
     * @param justAfter
     * @param urlWEB
     */
    public void open_after_tab(String justAfter,String urlWEB){
        Node p = new Node(urlWEB);
        p.lastNode = false;
        if(head==null){
            System.out.println("Linked list is empty.");
        }
        else{
            Node temp = head;
            while(!temp.url.equalsIgnoreCase(justAfter) && temp.next!=head){
                temp = temp.next;
            }
            if(temp.url.equalsIgnoreCase(justAfter)){
                p.next = temp.next;
                temp.next = p;
                p.lastNode = true;
            }
            else{
                System.out.println(red+bold+"URL not found!"+rest);
            }
        }
    }
    int count ()
    {
        Node temp=head;
        count =1;
        if ( head==null) {
            return 0;
        }
        while (temp.next != head)
        {
            count ++;
            temp = temp.next;
        }
        return count;
    }

    /**
     * The provided Java code defines a method named OpenedTabs within a class or
     * context that presumably manages a circular linked list of nodes representing tabs or URLs.
     * The purpose of this method is to display information about the opened tabs,
     * including their URLs and some formatting based on tab properties.
     */
    public void OpenTabsWhenNewNodeAdd(){
        if (head == null) {
            System.out.println("No tabs opened.");
        } else {
            int i = 1;
            Node temp = head;
            do {
                if (temp.lastNode) {
                    System.out.print("'" +bold+italic+ green + "" + temp.url + rest + "' --> ");
                } else if (temp.favourite) {
                    System.out.print("'" + bold+cyan +italic+ "" + temp.url + rest + "' --> ");
                }else {
                    int count = count();
                    if(i==count) {
                        System.out.print("'" + bold + yellow + italic + "" + temp.url + rest + "' --> ");
                    }
                    else{
                        System.out.print("'" +rest+bold+ red +italic+ temp.url + rest + "' --> ");
                    }
                }
                if (temp.next == null) {
                    break;
                }
                temp = temp.next;
                i++;
            } while (temp != head);
        }
    }
    public void OpenedTabes() {
        if (head == null) {
            System.out.println("No tabs opened.");
        } else {
            int i = 1;
            Node temp = head;
            do {
                if (temp.lastNode) {
                    System.out.print("'" +bold+italic+ green + "" + temp.url + rest + "' --> ");
                } else if (temp.favourite) {
                    System.out.print("'" + bold+cyan +italic+ "" + temp.url + rest + "' --> ");
                }else {
                    int count = count();

                    if(Web_Browser.prev) {
                        if(i==1) {
                            System.out.print("'" + bold + yellow + italic + "" + head.url + rest + "' --> ");
                        }
                        else{
                            System.out.print("'" +rest+bold+ red +italic+ temp.url + rest + "' --> ");
                        }
                    }
                    else if (Web_Browser.next){
                        if (i == count) {
                            System.out.print("'" + bold + yellow + italic + "" + temp.url + rest + "' --> ");
                        } else {
                            System.out.print("'" + rest + bold + red + italic + temp.url + rest + "' --> ");
                        }
                    }
                    else{
                        if (i == count) {
                            System.out.print("'" + bold + yellow + italic + "" + temp.url + rest + "' --> ");
                        } else {
                            System.out.print("'" + rest + bold + red + italic + temp.url + rest + "' --> ");
                        }
                    }

                }
                if (temp.next == null) {
                    break;
                }
                temp = temp.next;
                i++;
            } while (temp != head);
        }
    }


    /**
     * the removeAll method iterates through the circular linked list,
     * removing each node until it reaches the end of the list.
     * Finally, it sets the head to null to indicate an empty list.
     */
    public void removeAll() {
        if (head == null) {
            return;
        }

        Node current = head;
        while (current.next != head) {
            Node temp = current.next;
            current.next = temp.next;
            temp = null;
        }
        head = null;
    }

    /**
     * The BookMark method is responsible for marking a specific web page
     * with a bookmark in the circular linked list.
     * @param
     */
    public void bookmarkAndReplace(String urlWEB, String uniqueSign) {
        Node current = head;
        Node previous = null;
        boolean b = false;

        if (current == null) {
            System.out.println("The list is empty. Cannot bookmark and replace a node.");
            return;
        }

        do {
            if (current.url.equalsIgnoreCase(urlWEB+".com")) {
                current.bookMarked = true;
                current.uniqueSign = uniqueSign;
                System.out.println("The web page '" + urlWEB + "' has been bookmarked with the unique sign: " + uniqueSign);
                replaceNode(urlWEB+".com",urlWEB+".com"+uniqueSign);
                b = true;
            }
            previous = current;
            current = current.next;

        } while (current != head);
        if(b==false) {
            System.out.println(red+bold+"The web page '" + urlWEB + "' not found in the list."+rest);
        }
    }
    /**
     *The markAsFavorite method is responsible for marking a specific web page
     *as a favorite in the circular linked list.
     * @param searchUrl
     */
    public void markAsFavorite(String searchUrl) {
        Node current = head;

        if (current == null) {
            System.out.println("The list is empty. Cannot mark a favorite node.");
            return;
        }

        do {
            if (current.url.equalsIgnoreCase(searchUrl)) {
                current.favourite = true;
                System.out.println("The web page '" + searchUrl + "' has been marked as favorite.");
                return;
            }
            current = current.next;
        } while (current != head);

        System.out.println(red+bold+"The web page '" + searchUrl + "' is not opened."+rest);
    }
    /**
     * The showHistory method displays the history of visited web pages stored in the circular linked list.
     */

    public void showHistory(){
        if(head == null){
            System.out.println("Linked list is empty.");
        }
        else{
            int i = 0;
            Node temp = head;
            do{
                System.out.println("------------==========*---------------\n");
                if(temp.favourite){
                    System.out.print("Web page "+(i+1)+" : "+bold+cyan+temp.url+rest);
                }else{
                    if(i==0){
                        System.out.print("Web page "+(i+1)+" : "+bold+yellow+temp.url+rest);
                    }else{
                        System.out.print("Web page "+(i+1)+" : "+bold+red+temp.url+rest);
                    }
                }
                System.out.println("\n\n-----------==========*--------------");
                if(temp.next==null){
                    break;
                }
                temp = temp.next;
                i++;
            }while(temp!=head);
        }
    }

    /**
     *The removeFavorite method is responsible for removing the favorite status from a specific web page
     *  in the circular linked list.
     * @param searchUrl
     */
    public void removeFavorite(String searchUrl) {
        Node current = head;

        if (current == null) {
            System.out.println("The list is empty. Cannot remove favorite from an empty list.");
            return;
        }

        do {
            if (current.url.equalsIgnoreCase(searchUrl)) {
                current.favourite = false;
                System.out.println("The web  '" + searchUrl + "' has been removed from favorites.");
                return;
            }
            current = current.next;
        } while (current != head);

        System.out.println(bold+red+"The web pages '" + searchUrl + "' is not opened."+rest);
    }

    /**
     * The provided Java code defines a method named eliminateDuplicatesAndReplace within a class or
     * context that presumably manages a circular linked list of nodes representing tabs or URLs.
     * The purpose of this method is to remove duplicate nodes from the linked list based on their URLs.
     */
    public void eliminateDuplicatesAndReplace() {
        if (head == null) {
            System.out.println("No tabs opened.");
            return;
        }

        Node current = head;

        do {
            Node runner = current.next;
            Node prevRunner = current;

            while (runner != head) {
                if (current.url.equalsIgnoreCase(runner.url)) {
                    System.out.println("The tab '" + runner.url + "' is already open.");
                    // Remove the duplicate node (runner) from the list
                    prevRunner.next = runner.next;
                    runner = prevRunner.next;
                } else {
                    prevRunner = runner;
                    runner = runner.next;
                }
            }

            current = current.next;
        } while (current != head);
    }

    /**
     * The provided Java code defines a method named replaceNode within a class or context that presumably
     * manages a linked list of nodes representing tabs or URLs. The purpose of this method is to search
     * for a specific node with a given oldUrl and replace its URL with a new URL provided as newUrl
     * @param oldUrl
     * @param newUrl
     */
    public void replaceNode(String oldUrl, String newUrl) {
        if (head == null) {
            System.out.println("No tabs opened.");
            return;
        }

        Node current = head;

        do {
            if (current.url.equalsIgnoreCase(oldUrl)) {
                current.url = newUrl;
                return;
            }

            current = current.next;
        } while (current != head);

        System.out.println(bold+red+"The tab '" + oldUrl + "' not found in the list."+rest);
    }

    /**
     *The removeBookmark method is responsible for removing the bookmark status from
     *a specific web page in the circular linked list.
     * @param
     */
    public void removeBookmarkAndReplace(String urlWEB) {
        if (head == null) {
            System.out.println(bold+red+"No tabs opened."+rest);
            return;
        }

        Node current = head;

        do {
            if (current.url.equalsIgnoreCase(urlWEB+".com*")) {
                if (current.bookMarked) {
                    current.bookMarked = false;
                    current.uniqueSign = "";
                    System.out.println("Bookmark removed from the tab: '" + urlWEB + "'.");
                    replaceNode(urlWEB+".com"+"*",urlWEB+".com");

                } else {
                    System.out.println(bold+red+"The tab: '" + urlWEB + "' is not bookmarked."+rest);
                }
                return;
            }

            current = current.next;
        } while (current != head);

        System.out.println(bold+red+"The tab: '" + urlWEB + "' not found in the list"+rest);
    }
    public void webHistoryBackUp()throws Exception{
        f = new File("BackUp.txt");
        fw = new FileWriter(f);
        bw = new BufferedWriter(fw);
        if(head==null){
            System.out.println("No search for Back up.");
        }
        else{
            bw.write(myDate+"\n__________________________________________________________\n");
            int i = 0;
            Node temp = head;
            do{
                bw.write("["+myTime+"]Teb "+(i+1)+" : "+temp.url+"\n");
                temp = temp.next;
                i++;
            }while(temp!=head);
        }
        bw.close();
        fw.close();
    }
}
class Web_Browser{
    Web browser = new Web();
    Scanner sc = new Scanner(System.in);
    static String green="\u001b[32m";
    static String rest="\u001b[0m";
    static String red="\u001b[31m";
    static String cyan="\u001b[36m";
    static String yellow ="\u001B[33m";
    static String blue = "\u001B[34m";
    static String bold = "\u001B[1m";
    static String magenta ="\u001B[35m";
    static String italic = "\u001B[3m";
    static String st = "\u001B[5m";

    static boolean next = false;
    static boolean newNode = false;
    static boolean prev = false;

    /**
     *  This method allows the user to open a new tab by providing a URL.
     *  It prompts the user for a URL input and then adds the new tab to
     *  the browser using the addLast() method from the Web class.
     */
    public void openTab()throws Exception{
        newNode = true;
        if(newNode){
            System.out.println("Tab URL please : ");
            String url = sc.nextLine();
            browser.addLast(url+".com");
        }else{
            newNode = false;
        }
    }

    /**
     *This method moves to the next tab in the browser's tabs sequence.
     * It removes the current tab using removeFirst() from the Web class
     * and then adds it back to the end of the tabs sequence using addLast(),
     * effectively simulating moving to the next tab.
     */
    public void MoveToNextTab(){
        newNode = false;
        next =true;
        if(next) {
            String nextTab = browser.removeFirst();
            System.out.println("Next Tab :- " + nextTab);
            browser.addLast(nextTab);
        }
        else{
            next = false;
        }
    }

    /**
     *This method moves to the previous tab in the browser's tabs sequence.
     *  It removes the current tab using removeLast() from the Web class and
     *  then adds it back to the beginning of the tabs sequence using addFirst(),
     *  effectively simulating moving to the back tab.
     */
    public void MoveToBackTab(){
        newNode = false;
        prev = true;
        if(prev) {
            String previousTab = browser.removeLast();
            System.out.println("Previous Tab :- " + previousTab);
            browser.addFirst(previousTab);
        }
        else{
            prev = false;
        }
    }

    /**
     * This method allows the user to close a specific tab by providing its URL.
     * It prompts the user for a URL input and then uses the close_perticular_tab() method
     * from the Web class to close the specified tab.
     */
    public void closePerticularTab(){
        System.out.println("Tab url please... : ");
        String url = sc.nextLine();
        browser.close_perticular_tab(url+".com");
    }

    /**
     * This method opens a new tab just before a specified tab.
     * It prompts the user for the URL of the tab they want to open before and the URL of the new tab.
     * It uses the open_Tab_before_any_tab() method from the Web class to achieve this.
     */
    public void openTabBeforePerticularTab()throws Exception{
        System.out.println("Just after Tab url please..... : ");
        String justAfterTab = sc.nextLine();
        justAfterTab = justAfterTab+".com";
        System.out.println("Tab url that you want to open just before "+justAfterTab+" .....  : ");
        String newTab = sc.nextLine();
        newTab = newTab+".com";

        browser.open_Tab_before_any_tab(justAfterTab,newTab);
    }

    /**
     * This method opens a new tab just after a specified tab.
     * It prompts the user for the URL of the tab they want to open after and the URL of the new tab.
     * It uses the open_after_tab() method from the Web class to achieve this.
     */
    public void openTabAfterPerticularTab()throws Exception{
        System.out.println("Just before tab Url please..... : ");
        String justBeforeTab = sc.nextLine();
        justBeforeTab = justBeforeTab+".com";
        System.out.println("Tab url that you want to open just after "+justBeforeTab+" .....  : ");
        String newTab = sc.nextLine();
        newTab = newTab+".com";

        browser.open_after_tab(justBeforeTab,newTab);
    }
    public void eliminateDuplicates(){
        browser.eliminateDuplicatesAndReplace();
    }

    /**
     * This method provides a user interface for marking and unmarking tabs as favorites.
     * It presents a menu to the user with options to mark a tab as a favorite, unmark a favorite, or exit.
     * It uses the markAsFavorite() and removeFavorite() methods from the Web class to manage the favorite status of tabs.
     */
    public void markAsFavorite(){
        boolean b = true;
        while(b) {
            System.out.println(bold+yellow+"_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ "+rest);
            System.out.printf(bold+yellow+"|"+rest+" %-49s "+bold+yellow+"|"+rest+"%n",bold+blue+"1 : for mark as favorite"+rest);
            System.out.println(bold+yellow+"|_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ |"+rest);
            System.out.printf(bold+yellow+"|"+rest+" %-49s "+bold+yellow+"|"+rest+"%n",bold+blue+"2 : deSelect from mark as favorite."+rest);
            System.out.println(bold+yellow+"|_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ |"+rest);
            System.out.printf(bold+yellow+"|"+rest+" %-49s "+bold+yellow+"|"+rest+"%n",bold+blue+"3 : EXIT."+rest);
            System.out.println(bold+yellow+"|_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ |"+rest);
            String choice = sc.nextLine();
            switch (choice){
                case "1" :
                    System.out.println("Select tab as favorite..... : ");
                    String favTab = sc.nextLine();
                    browser.markAsFavorite(favTab+".com");
                    break;
                case "2" :
                    System.out.println("DeSelect tab as favorite..... : ");
                    String removeFavTab = sc.nextLine();
                    browser.removeFavorite(removeFavTab+".com");
                    break;
                case "3" :
                    b = false;
                    break;
                default :
                    System.out.println("Press on only valid key.");
            }
        }
    }

    /**
     * This method provides a user interface for bookmarking and removing bookmarks from tabs.
     * It presents a menu to the user with options to mark a tab as a bookmark, remove a bookmark, or exit.
     * It uses the BookMark() and removeBookmark() methods from the Web class to manage the bookmark status of tabs.
     */
    public void markAsBookMarked(){
        boolean b = true;
        while(b) {
            System.out.println(bold+yellow+"_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ "+rest);
            System.out.printf(bold+yellow+"|"+rest+" %-49s "+bold+yellow+"|"+rest+"%n",bold+blue+"1 : for mark as BookMark"+rest);
            System.out.println(bold+yellow+"|_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ |"+rest);
            System.out.printf(bold+yellow+"|"+rest+" %-49s "+bold+yellow+"|"+rest+"%n",bold+blue+"2 : deSelect from mark as BookMark."+rest);
            System.out.println(bold+yellow+"|_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ |"+rest);
            System.out.printf(bold+yellow+"|"+rest+" %-49s "+bold+yellow+"|"+rest+"%n",bold+blue+"3 : EXIT."+rest);
            System.out.println(bold+yellow+"|_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ |"+rest);
            String choice = sc.nextLine();
            switch (choice){
                case "1" :
                    System.out.println("Select tab as for Book mark..... : ");
                    String BMTab = sc.nextLine();
                    browser.bookmarkAndReplace(BMTab,"*");
                    break;
                case "2" :
                    System.out.println("DeSelect book marked tab..... : ");
                    String removeBMTab = sc.nextLine();
                    browser.removeBookmarkAndReplace(removeBMTab);
                    break;
                case "3" :
                    b = false;
                    break;
                default :
                    System.out.println("Press on only valid key.");
            }
        }
    }
    /**
     * This method displays the history of opened tabs in a formatted manner.
     * It uses the showHistory() method from the Web class to show the history of visited tabs.
     */
    public void showAllHistoryOfSearchingTabs(){
        browser.showHistory();
    }

    /**
     * This method displays the currently opened tabs.
     * It seems like a part of a hypothetical method to show the opened tabs in the browser.
     */
    public void OpenTabsOfChrome(){
        if(newNode) {
            browser.OpenTabsWhenNewNodeAdd();
        }
        else {
            browser.OpenedTabes();
        }
    }

    public void closeAllTabAtATimeAOpenAnother(){
        browser.removeAll();
    }
    public void backUp()throws Exception{
        browser.webHistoryBackUp();
    }
}

/**
 * The DS_Project class acts as the main program that simulates a basic
 * web browser using the functionalities provided by the Web_Browser class.
 * @author Harsh
 * @since  2007
 * @see <a href = "https://docs.oracle.com/en/java/javase/20/docs/api/index.html"target="_blank">Java Documantation</a>
 * @version 1.1
 */
public class DS_Project {
    /**
     * The main method in the provided code serves as the entry point for the program.
     * It enables interaction with a basic simulated web browser
     * interface by utilizing the functionalities of the Web_Browser class.
     * @param args
     */
    static String green="\u001b[32m";
    static String underLine = "\u001b[4m";
    static String italicize = "\u001b[3m";
    static String rest="\u001b[0m";
    static String red="\u001b[31m";
    static String cyan="\u001b[36m";
    static String yellow ="\u001B[33m";
    static String blue = "\u001B[34m";
    static String bold = "\u001B[1m";
    static String magenta ="\u001B[35m";
    static String st = "\u001B[5m";

    public static void main(String[] args)throws Exception {
        Scanner sc = new Scanner(System.in);

        Web_Browser web = new Web_Browser();
        boolean b = true;
        while(b){
            System.out.println(italicize+bold+"_ _ _ _ _ _ _ _ _"+rest);
            System.out.println(italicize+bold+"| OPENED TAB :- |"+rest);
            System.out.println(italicize+bold+magenta+"________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________"+rest);
            System.out.print("| ");web.OpenTabsOfChrome();
            System.out.println(italicize+bold+magenta+"\n________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________"+rest);
            System.out.println(bold+yellow+"\n\n_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ __\n" +rest+
                    bold+yellow+"|            \t\t\t\t\t\t"+underLine+italicize+bold+red+"Web Browser - Main Menu"+rest+"\t\t\t\t\t\t"+bold+yellow+"               |"+rest);
            System.out.println(bold+yellow+"| _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _|"+rest);
            System.out.printf("| %-50s | %-66s|%n",bold+blue+"O : Open a new Tab"+rest,bold+blue+" BM : book mark or deselect from Book mark"+rest);
            System.out.printf("| %-50s | %-66s|%n",bold+blue+"> : Move To Next Tab"+rest,bold+blue+" C : Close Particular Tab"+rest);
            System.out.printf("| %-50s | %-66s|%n",bold+blue+"< : Move To Previous Tab"+rest,bold+blue+" S : Show Search History"+rest);
            System.out.printf("| %-50s | %-66s|%n",bold+blue+"B : Open Tab Before Particular Tab"+rest,bold+blue+" F : mark as favorite or deselect from favorite"+rest);
            System.out.printf("| %-50s | %-66s|%n",bold+blue+"A : Open Tab After Particular Tab"+rest,bold+blue+" CA : Close all Tabs a time and open a tab"+rest);
            System.out.println(bold+yellow+"|_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ |"+rest);
            System.out.printf(bold+yellow+"| "+rest+"%-40s %-10s %-25s"+bold+yellow+"|"+rest+"%n","",bold+blue+"EX : exit from web Browser"+rest,"");
            System.out.println(bold+yellow+"|_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ __|"+rest);

            String choice = sc.nextLine().toUpperCase();
            switch(choice){
                case "O" :
                    web.openTab();
                    web.eliminateDuplicates();
                    web.backUp();
                    break;
                case ">":
                    web.MoveToNextTab();
                    break;
                case "<" :
                    web.MoveToBackTab();
                    break;
                case "B" :
                    web.openTabBeforePerticularTab();
                    web.eliminateDuplicates();
                    break;
                case "A" :
                    web.openTabAfterPerticularTab();
                    web.eliminateDuplicates();
                    break;
                case "C" :
                    web.closePerticularTab();
                    break;
                case "S" :
                    web.showAllHistoryOfSearchingTabs();
                    break;
                case "BM" :
                    web.markAsBookMarked();
                    break;
                case "F" :
                    web.markAsFavorite();
                    break;
                case "CA" :
                    web.closeAllTabAtATimeAOpenAnother();
                    break;
                case  "EX" :
                    b = false;
                    break;
                default :
                    System.out.println("Valid choice only.");
                    break;
            }
        }
    }
}
