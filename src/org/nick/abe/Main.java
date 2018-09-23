package org.nick.abe;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;


public class Main {

    public static String VERSION = "v 1.5.0";

    public static void main(String[] args) {
        Security.addProvider(new BouncyCastleProvider());

        String backupFilename = "fullbackupdata";
        String tarFilename = "restore.tar";
        String password;
        if (args.length >= 1) {
            password = args[0];
            System.out.println("Starting PC companion Backup extractor " + VERSION + " with password protection");
        }
        else {
            /* if password is not given, try to read it from environment */
            password = System.getenv("ABE_PASSWD");
            System.out.println("Starting PC companion Backup extractor " + VERSION + " without password protection");
        }

        AndroidBackup.extractAsTar(backupFilename, tarFilename, password);
    }

    private static void usage() {
        System.out.println("Usage:");
        System.out
                .println("  unpack:\tabe unpack\t<backup.ab> <backup.tar> [password]");
        System.out
                .println("  pack:\t\tabe pack\t<backup.tar> <backup.ab> [password]");
        System.out
                .println("  pack for 4.4:\tabe pack-kk\t<backup.tar> <backup.ab> [password]");
        System.out
                .println("If the filename is `-`, then data is read from standard input");
        System.out
                .println("or written to standard output.");
        System.out
                .println("Envvar ABE_PASSWD is tried when password is not given");
    }

}
