package main.java.wordsim;

import main.java.wordsim.animals.*;
import main.java.wordsim.plants.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class World {
    protected int rows, cols, turn;
    protected Human human;
    protected ArrayList<Organism> order;
    protected JTextArea logs;
    protected JPanel[][] cells;
    protected JFrame frame;

    public World(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.turn = 1;
        this.human = null;
        this.order = new ArrayList<>();
        this.logs = new JTextArea();
        this.cells = new JPanel[rows][cols];
        this.frame = new JFrame("Simulator");

        logs.append("-- Turn 0 --\n");

        this.initWindow();
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public boolean addOrganism(Organism organism) {
        boolean addedHuman = false;

        // Only one human can be present in the world
        if (organism instanceof Human) {
            if (human == null) {
                human = (Human) organism;
                addedHuman = true;
            }
            else {
                return false;
            }
        }

        if (organism.getY() < rows && organism.getX() < cols && organism.getY() >= 0 && organism.getX() >= 0) {
            organism.setWorld(this);
            order.add(organism);
            appendLog(organism.getName() + ": Was created\n");
            return true;
        }
        else {
            if (addedHuman) {
                human = null;
            }
            return false;
        }
    }

    public Organism getOrganism(int y, int x) {
        for (Organism organism : order) {
            if (!organism.isDead() && organism.getY() == y && organism.getX() == x) {
                return organism;
            }
        }

        return null;
    }

    public void appendLog(String message) {
        logs.append(message);
    }

    protected void removeDead() {
        for (int i = 0; i < order.size(); i++) {
            if (order.get(i).isDead()) {
                if (order.get(i) instanceof Human) {
                    human = null;
                }
                order.remove(i);
                i--;
            }
        }
    }

    protected void sortOrder() {
        for (int i = 0; i < order.size() - 1; i++) {
            for (int j = i + 1; j < order.size(); j++) {
                if (order.get(j).getInitiative() > order.get(i).getInitiative()) {
                    Collections.swap(order, i, j);
                }
                else if (order.get(j).getInitiative() == order.get(i).getInitiative() && order.get(j).getAge() > order.get(i).getAge()) {
                    Collections.swap(order, i, j);
                }
            }
        }
    }

    protected void initWindow() {
        // Initialize frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        // Initialize game panel
        JPanel gamePanel = new JPanel(new GridLayout(this.rows, this.cols, 1, 1));
        gamePanel.setBackground(Color.BLACK);
        gamePanel.setFocusable(true);

        // Creating and adding cells to a grid
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                JPanel cell = new JPanel();
                JLabel text = new JLabel();
                cell.setPreferredSize(new Dimension(25, 25));
                cell.add(text);

                // Create popup menu
                JPopupMenu creationMenu = new JPopupMenu();

                int currentRow = i;
                int currentCol = j;

                JMenuItem dandelionItem = new JMenuItem("Dandelion");
                dandelionItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addOrganism(new Dandelion(currentRow, currentCol));
                        updateGame();
                        creationMenu.setVisible(false);
                    }
                });
                creationMenu.add(dandelionItem);

                JMenuItem deadlyNightshadeItem = new JMenuItem("DeadlyNightshade");
                deadlyNightshadeItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addOrganism(new DeadlyNightshade(currentRow, currentCol));
                        updateGame();
                        creationMenu.setVisible(false);
                    }
                });
                creationMenu.add(deadlyNightshadeItem);

                JMenuItem grassItem = new JMenuItem("Grass");
                grassItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addOrganism(new Grass(currentRow, currentCol));
                        updateGame();
                        creationMenu.setVisible(false);
                    }
                });
                creationMenu.add(grassItem);

                JMenuItem guaranaItem = new JMenuItem("Guarana");
                guaranaItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addOrganism(new Guarana(currentRow, currentCol));
                        updateGame();
                        creationMenu.setVisible(false);
                    }
                });
                creationMenu.add(guaranaItem);

                JMenuItem hogweedItem = new JMenuItem("Hogweed");
                hogweedItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addOrganism(new Hogweed(currentRow, currentCol));
                        updateGame();
                        creationMenu.setVisible(false);
                    }
                });
                creationMenu.add(hogweedItem);

                JMenuItem antelopeItem = new JMenuItem("Antelope");
                antelopeItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addOrganism(new Antelope(currentRow, currentCol));
                        updateGame();
                        creationMenu.setVisible(false);
                    }
                });
                creationMenu.add(antelopeItem);

                JMenuItem foxItem = new JMenuItem("Fox");
                foxItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addOrganism(new Fox(currentRow, currentCol));
                        updateGame();
                        creationMenu.setVisible(false);
                    }
                });
                creationMenu.add(foxItem);

                JMenuItem sheepItem = new JMenuItem("Sheep");
                sheepItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addOrganism(new Sheep(currentRow, currentCol));
                        updateGame();
                        creationMenu.setVisible(false);
                    }
                });
                creationMenu.add(sheepItem);

                JMenuItem turtleItem = new JMenuItem("Turtle");
                turtleItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addOrganism(new Turtle(currentRow, currentCol));
                        updateGame();
                        creationMenu.setVisible(false);
                    }
                });
                creationMenu.add(turtleItem);

                JMenuItem wolfItem = new JMenuItem("Wolf");
                wolfItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addOrganism(new Wolf(currentRow, currentCol));
                        updateGame();
                        creationMenu.setVisible(false);
                    }
                });
                creationMenu.add(wolfItem);

                JMenuItem humanItem = new JMenuItem("Human");
                humanItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addOrganism(new Human(currentRow, currentCol));
                        updateGame();
                        creationMenu.setVisible(false);
                    }
                });
                creationMenu.add(humanItem);

                cell.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (getOrganism(currentRow, currentCol) == null) {
                            humanItem.setEnabled(human == null);

                            // Show menu at mouse cursor
                            creationMenu.show(e.getComponent(), e.getX(), e.getY());

                            // Close after pressing any key
                            Component invoker = e.getComponent();

                            KeyListener closeOnKey = new KeyAdapter() {
                                @Override
                                public void keyPressed(KeyEvent e) {
                                    creationMenu.setVisible(false);
                                    invoker.removeKeyListener(this);
                                }
                            };

                            invoker.addKeyListener(closeOnKey);
                            invoker.requestFocusInWindow();
                        }
                    }
                });

                gamePanel.add(cell);
                cells[i][j] = cell;
            }
        }

        // Handle key inputs
        // Up
        gamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"), "moveUp");
        gamePanel.getActionMap().put("moveUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (human != null) {
                    human.setNextMove('w');
                    takeTurn();
                }
            }
        });
        // Down
        gamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "moveDown");
        gamePanel.getActionMap().put("moveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (human != null) {
                    human.setNextMove('s');
                    takeTurn();
                }
            }
        });
        // Left
        gamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "moveLeft");
        gamePanel.getActionMap().put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (human != null) {
                    human.setNextMove('a');
                    takeTurn();
                }
            }
        });
        // Right
        gamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "moveRight");
        gamePanel.getActionMap().put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (human != null) {
                    human.setNextMove('d');
                    takeTurn();
                }
            }
        });
        // Ability
        gamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F"), "activateAbility");
        gamePanel.getActionMap().put("activateAbility", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (human != null && human.getAbilityTimer() == - 5) {
                    human.setAbilityTimer(5);
                    updateGame();
                }
            }
        });

        gamePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 3, Color.BLACK));
        frame.add(gamePanel, BorderLayout.CENTER);

        // Initialize button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));

        JButton loadButton = new JButton("Load");
        loadButton.setFocusable(false);

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load();
            }
        });
        buttonPanel.add(loadButton);

        JButton saveButton = new JButton("Save");
        saveButton.setFocusable(false);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        buttonPanel.add(saveButton);

        JButton nextTurnButton = new JButton("Next turn");
        nextTurnButton.setFocusable(false);

        nextTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (human != null) {
                    human.setNextMove(' ');
                }
                takeTurn();
            }
        });
        buttonPanel.add(nextTurnButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Initialize log area
        logs.setEditable(false);
        logs.setLineWrap(true);
        logs.setWrapStyleWord(true);
        logs.setFocusable(false);
        logs.setFont(new Font("Serif", Font.PLAIN, 14));

        JScrollPane logPane = new JScrollPane(logs);
        logPane.setPreferredSize(new Dimension( 400, 0));

        frame.add(logPane, BorderLayout.EAST);


        updateGame();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    protected void clearGame() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                JPanel cell = cells[i][j];
                cell.setBackground(Color.WHITE);
                JLabel text = (JLabel) cell.getComponent(0);
                text.setText("");
            }
        }
    }

    public void updateGame() {
        clearGame();

        for (Organism organism : order) {
            if (!organism.isDead()) {
                JPanel cell = cells[organism.getY()][organism.getX()];
                cell.setBackground(organism.getColor());

                if (organism instanceof Human && human.getAbilityTimer() > 0) {
                    cell.setBackground(human.getAbilityColor());
                }

                JLabel text = (JLabel) cell.getComponent(0);
                text.setText(Character.toString(organism.getSymbol()));
            }
        }

        // Display latest logs
        logs.setCaretPosition(logs.getDocument().getLength());

        frame.revalidate();
        frame.repaint();
    }

    protected void takeTurn() {
        sortOrder();

        // Number of organisms before the turn
        int n = order.size();

        appendLog("-- Turn " + Integer.toString(turn) + " --\n");

        // Only organisms that are alive and created before the turn will take action
        for (int i = 0; i < n; i++) {
            if (!order.get(i).isDead()) {
                order.get(i).action();
            }
        }

        removeDead();
        updateGame();
        turn++;
    }

    public void save() {
        try {
            PrintWriter saveFile = new PrintWriter(new FileWriter("save.txt"));
            saveFile.println(rows);
            saveFile.println(cols);
            saveFile.println(turn);
            saveFile.println("---");
            for (Organism organism : order) {
                saveFile.println(organism.getName().split(" ")[0] + ";" + organism.getY() + ";" + organism.getX() + ";" + organism.getAge() + ";" + organism.getStrength());
            }
            saveFile.println("---");

            saveFile.println(logs.getText());

            if (human != null) {
                saveFile.println("---");
                saveFile.println(human.getAbilityTimer());
            }

            saveFile.close();
        }
        catch (IOException e) {
            appendLog("ERROR: Cannot save the game!");
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            BufferedReader saveFile = new BufferedReader(new FileReader("save.txt"));

            String line;

            this.rows = Integer.parseInt(saveFile.readLine());
            this.cols = Integer.parseInt(saveFile.readLine());
            this.turn = Integer.parseInt(saveFile.readLine());

            this.human = null;
            this.order = new ArrayList<>();
            this.cells = new JPanel[rows][cols];

            this.frame.dispose();
            this.frame = new JFrame("Simulator");

            line = saveFile.readLine(); // ---

            while((line = saveFile.readLine()) != null) {
                if (line.equals("---")) {
                    break;
                }

                String name = line.split(";")[0];
                int y = Integer.parseInt(line.split(";")[1]);
                int x = Integer.parseInt(line.split(";")[2]);
                int age = Integer.parseInt(line.split(";")[3]);
                int strength = Integer.parseInt(line.split(";")[4]);

                Organism newOrganism = null;

                switch (name) {
                    case "Antelope":
                        newOrganism = new Antelope(y, x);
                        break;
                    case "Fox":
                        newOrganism = new Fox(y, x);
                        break;
                    case "Human":
                        newOrganism = new Human(y, x);
                        break;
                    case "Sheep":
                        newOrganism = new Sheep(y, x);
                        break;
                    case "Turtle":
                        newOrganism = new Turtle(y, x);
                        break;
                    case "Wolf":
                        newOrganism = new Wolf(y, x);
                        break;
                    case "Dandelion":
                        newOrganism = new Dandelion(y, x);
                        break;
                    case "Deadly":
                        newOrganism = new DeadlyNightshade(y, x);
                        break;
                    case "Grass":
                        newOrganism = new Grass(y, x);
                        break;
                    case "Guarana":
                        newOrganism = new Guarana(y, x);
                        break;
                    case "Hogweed":
                        newOrganism = new Hogweed(y, x);
                        break;
                }

                if (newOrganism != null) {
                    newOrganism.setAge(age);
                    newOrganism.setStrength(strength);

                    addOrganism(newOrganism);
                }
            }

            this.logs = new JTextArea();

            while((line = saveFile.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    break;
                }
                appendLog(line + "\n");
            }

            if (human != null) {
                line = saveFile.readLine();
                human.setAbilityTimer(Integer.parseInt(saveFile.readLine()));
            }

            saveFile.close();
            initWindow();
        }
        catch (IOException e) {
            appendLog("ERROR: Cannot load the game!");
            e.printStackTrace();
        }
    }
}
