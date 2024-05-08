if (args.length == 0) {
            System.err.println("Usage: java HashGeneratorServer <message>");
            return null;
        }

        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server in ascolto sulla porta " + 8080);

            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Nuova connessione da " + clientSocket.getInetAddress().getHostAddress());
                    
                    PrintWriter out;
                    try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                        out = new PrintWriter(clientSocket.getOutputStream(), true);
                        String message = in.readLine();
                        if(message != null)
                            out.println(message);//TODO edit
                        else
                            System.out.println("Errore, il messaggio è vuoto.");
                    }
                    out.close();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }