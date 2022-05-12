#!/usr/bin/perl

$pref = "MST_LP_";
$command = "java -jar ../target/MO-0.10.0.jar 3 ../assets/";

opendir DIR, "../assets" || die "Directory not found";

@files = readdir(DIR);
@files = grep(/\w+.txt/, @files);

foreach $name (@files) {
	system($command . $name . " > ../assets/temat10/" . $pref . $name);
}

closedir(DIR);
# cd ../
# java -jar  ./target/MO_0.10.0.jar 3 ./assets
