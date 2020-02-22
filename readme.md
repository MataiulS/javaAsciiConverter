#IMAGE =====> ASCII

Java ASCII converter CLI.
    
   ##PARAMETERS
    -ip -> Image absolute path;
    -dp -> Optional absolute path to copy generated ASCII;
    -w -h -> Optional. Must be used together. Specifiy width and height of ASCII;
    -print -> Optional. Don't dump ASCII to file, instead, print it on terminal;

##USAGE EXAMPLE
    java Main -ip path_to_image.jpg -dp path_to_file
    java Main -ip path_to_image.jpg -print
    java Main -ip path_to_image.jpg -w 500 -h 500