/*
 * Copyright (c) 2009-2016 Matthew R. Harrah
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package org.gedcom4j.model;

import java.util.ArrayList;
import java.util.List;

import org.gedcom4j.Options;

/**
 * Header information about the GEDCOM file
 * 
 * @author frizbog1
 * 
 */
public class Header extends AbstractElement {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 2648392706111388922L;

    /**
     * The character set in use in the GEDCOM file
     */
    private CharacterSet characterSet = new CharacterSet();

    /**
     * Copyright information for the GEDCOM file.
     */
    private List<String> copyrightData = getCopyrightData(Options.isCollectionInitializationEnabled());

    /**
     * The date of the GEDCOM file
     */
    private StringWithCustomTags date;

    /**
     * The destination system for the GEDCOM file.
     */
    private StringWithCustomTags destinationSystem;

    /**
     * The filename for the GEDCOM file
     */
    private StringWithCustomTags fileName;

    /**
     * The version information for the GEDCOM file
     */
    private GedcomVersion gedcomVersion = new GedcomVersion();

    /**
     * The language for the file
     */
    private StringWithCustomTags language;

    /**
     * Notes on this header. Technically, the spec does not allow multiple notes or multiline notes in headers, but it
     * happens so often it's better to allow it than to stop people from being able to parse files.
     */
    private List<Note> notes = getNotes(Options.isCollectionInitializationEnabled());

    /**
     * The place structure for the file
     */
    private StringWithCustomTags placeHierarchy;

    /**
     * The source system for the GEDCOM file
     */
    private SourceSystem sourceSystem = new SourceSystem();

    /**
     * Information about the file submission
     */
    private Submission submission;

    /**
     * Information about the submitter of the file
     */
    private Submitter submitter;

    /**
     * The time of the file
     */
    private StringWithCustomTags time;

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(IVisitor v) {
        v.visit(this);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("PMD.ExcessiveMethodLength")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Header other = (Header) obj;
        if (characterSet == null) {
            if (other.characterSet != null) {
                return false;
            }
        } else if (!characterSet.equals(other.characterSet)) {
            return false;
        }
        if (copyrightData == null) {
            if (other.copyrightData != null) {
                return false;
            }
        } else if (!copyrightData.equals(other.copyrightData)) {
            return false;
        }
        if (date == null) {
            if (other.date != null) {
                return false;
            }
        } else if (!date.equals(other.date)) {
            return false;
        }
        if (destinationSystem == null) {
            if (other.destinationSystem != null) {
                return false;
            }
        } else if (!destinationSystem.equals(other.destinationSystem)) {
            return false;
        }
        if (fileName == null) {
            if (other.fileName != null) {
                return false;
            }
        } else if (!fileName.equals(other.fileName)) {
            return false;
        }
        if (gedcomVersion == null) {
            if (other.gedcomVersion != null) {
                return false;
            }
        } else if (!gedcomVersion.equals(other.gedcomVersion)) {
            return false;
        }
        if (language == null) {
            if (other.language != null) {
                return false;
            }
        } else if (!language.equals(other.language)) {
            return false;
        }
        if (notes == null) {
            if (other.notes != null) {
                return false;
            }
        } else if (!notes.equals(other.notes)) {
            return false;
        }
        if (placeHierarchy == null) {
            if (other.placeHierarchy != null) {
                return false;
            }
        } else if (!placeHierarchy.equals(other.placeHierarchy)) {
            return false;
        }
        if (sourceSystem == null) {
            if (other.sourceSystem != null) {
                return false;
            }
        } else if (!sourceSystem.equals(other.sourceSystem)) {
            return false;
        }
        if (submission == null) {
            if (other.submission != null) {
                return false;
            }
        } else if (!submission.equals(other.submission)) {
            return false;
        }
        if (submitter == null) {
            if (other.submitter != null) {
                return false;
            }
        } else if (!submitter.equals(other.submitter)) {
            return false;
        }
        if (time == null) {
            if (other.time != null) {
                return false;
            }
        } else if (!time.equals(other.time)) {
            return false;
        }
        return true;
    }

    /**
     * Get the characterSet
     * 
     * @return the characterSet
     */
    public CharacterSet getCharacterSet() {
        return characterSet;
    }

    /**
     * Get the copyright data
     * 
     * @return the copyright data
     */
    public List<String> getCopyrightData() {
        return copyrightData;
    }

    /**
     * Get the copyright data
     * 
     * @param initializeIfNeeded
     *            initialize the collection if needed?
     * @return the copyright data
     */
    public List<String> getCopyrightData(boolean initializeIfNeeded) {
        if (initializeIfNeeded && copyrightData == null) {
            copyrightData = new ArrayList<String>(0);
        }
        return copyrightData;
    }

    /**
     * Get the date
     * 
     * @return the date
     */
    public StringWithCustomTags getDate() {
        return date;
    }

    /**
     * Get the destinationSystem
     * 
     * @return the destinationSystem
     */
    public StringWithCustomTags getDestinationSystem() {
        return destinationSystem;
    }

    /**
     * Get the fileName
     * 
     * @return the fileName
     */
    public StringWithCustomTags getFileName() {
        return fileName;
    }

    /**
     * Get the gedcomVersion
     * 
     * @return the gedcomVersion
     */
    public GedcomVersion getGedcomVersion() {
        return gedcomVersion;
    }

    /**
     * Get the language
     * 
     * @return the language
     */
    public StringWithCustomTags getLanguage() {
        return language;
    }

    /**
     * Get the notes
     * 
     * @return the notes
     */
    public List<Note> getNotes() {
        return notes;
    }

    /**
     * Get the notes
     * 
     * @param initializeIfNeeded
     *            true if this collection should be created on-the-fly if it is currently null
     * @return the notes
     */
    public List<Note> getNotes(boolean initializeIfNeeded) {
        if (initializeIfNeeded && notes == null) {
            notes = new ArrayList<Note>(0);
        }
        return notes;
    }

    /**
     * Get the placeHierarchy
     * 
     * @return the placeHierarchy
     */
    public StringWithCustomTags getPlaceHierarchy() {
        return placeHierarchy;
    }

    /**
     * Get the sourceSystem
     * 
     * @return the sourceSystem
     */
    public SourceSystem getSourceSystem() {
        return sourceSystem;
    }

    /**
     * Get the submission
     * 
     * @return the submission
     */
    public Submission getSubmission() {
        return submission;
    }

    /**
     * Get the submitter
     * 
     * @return the submitter
     */
    public Submitter getSubmitter() {
        return submitter;
    }

    /**
     * Get the time
     * 
     * @return the time
     */
    public StringWithCustomTags getTime() {
        return time;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (characterSet == null ? 0 : characterSet.hashCode());
        result = prime * result + (copyrightData == null ? 0 : copyrightData.hashCode());
        result = prime * result + (date == null ? 0 : date.hashCode());
        result = prime * result + (destinationSystem == null ? 0 : destinationSystem.hashCode());
        result = prime * result + (fileName == null ? 0 : fileName.hashCode());
        result = prime * result + (gedcomVersion == null ? 0 : gedcomVersion.hashCode());
        result = prime * result + (language == null ? 0 : language.hashCode());
        result = prime * result + (notes == null ? 0 : notes.hashCode());
        result = prime * result + (placeHierarchy == null ? 0 : placeHierarchy.hashCode());
        result = prime * result + (sourceSystem == null ? 0 : sourceSystem.hashCode());
        result = prime * result + (submission == null ? 0 : submission.hashCode());
        result = prime * result + (submitter == null ? 0 : submitter.hashCode());
        result = prime * result + (time == null ? 0 : time.hashCode());

        return result;
    }

    /**
     * Set the characterSet
     * 
     * @param characterSet
     *            the characterSet to set
     */
    public void setCharacterSet(CharacterSet characterSet) {
        this.characterSet = characterSet;
    }

    /**
     * Set the date
     * 
     * @param date
     *            the date to set
     */
    public void setDate(StringWithCustomTags date) {
        this.date = date;
    }

    /**
     * Set the destinationSystem
     * 
     * @param destinationSystem
     *            the destinationSystem to set
     */
    public void setDestinationSystem(StringWithCustomTags destinationSystem) {
        this.destinationSystem = destinationSystem;
    }

    /**
     * Set the fileName
     * 
     * @param fileName
     *            the fileName to set
     */
    public void setFileName(StringWithCustomTags fileName) {
        this.fileName = fileName;
    }

    /**
     * Set the gedcomVersion
     * 
     * @param gedcomVersion
     *            the gedcomVersion to set
     */
    public void setGedcomVersion(GedcomVersion gedcomVersion) {
        this.gedcomVersion = gedcomVersion;
    }

    /**
     * Set the language
     * 
     * @param language
     *            the language to set
     */
    public void setLanguage(StringWithCustomTags language) {
        this.language = language;
    }

    /**
     * Set the placeHierarchy
     * 
     * @param placeHierarchy
     *            the placeHierarchy to set
     */
    public void setPlaceHierarchy(StringWithCustomTags placeHierarchy) {
        this.placeHierarchy = placeHierarchy;
    }

    /**
     * Set the sourceSystem
     * 
     * @param sourceSystem
     *            the sourceSystem to set
     */
    public void setSourceSystem(SourceSystem sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    /**
     * Set the submission
     * 
     * @param submission
     *            the submission to set
     */
    public void setSubmission(Submission submission) {
        this.submission = submission;
    }

    /**
     * Set the submitter
     * 
     * @param submitter
     *            the submitter to set
     */
    public void setSubmitter(Submitter submitter) {
        this.submitter = submitter;
    }

    /**
     * Set the time
     * 
     * @param time
     *            the time to set
     */
    public void setTime(StringWithCustomTags time) {
        this.time = time;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Header [");
        if (characterSet != null) {
            builder.append("characterSet=");
            builder.append(characterSet);
            builder.append(", ");
        }
        if (copyrightData != null) {
            builder.append("copyrightData=");
            builder.append(copyrightData);
            builder.append(", ");
        }
        if (date != null) {
            builder.append("date=");
            builder.append(date);
            builder.append(", ");
        }
        if (destinationSystem != null) {
            builder.append("destinationSystem=");
            builder.append(destinationSystem);
            builder.append(", ");
        }
        if (fileName != null) {
            builder.append("fileName=");
            builder.append(fileName);
            builder.append(", ");
        }
        if (gedcomVersion != null) {
            builder.append("gedcomVersion=");
            builder.append(gedcomVersion);
            builder.append(", ");
        }
        if (language != null) {
            builder.append("language=");
            builder.append(language);
            builder.append(", ");
        }
        if (notes != null) {
            builder.append("notes=");
            builder.append(notes);
            builder.append(", ");
        }
        if (placeHierarchy != null) {
            builder.append("placeHierarchy=");
            builder.append(placeHierarchy);
            builder.append(", ");
        }
        if (sourceSystem != null) {
            builder.append("sourceSystem=");
            builder.append(sourceSystem);
            builder.append(", ");
        }
        if (submission != null) {
            builder.append("submission=");
            builder.append(submission);
            builder.append(", ");
        }
        if (submitter != null) {
            builder.append("submitter=");
            builder.append(submitter);
            builder.append(", ");
        }
        if (time != null) {
            builder.append("time=");
            builder.append(time);
            builder.append(", ");
        }
        if (customTags != null) {
            builder.append("customTags=");
            builder.append(customTags);
        }
        builder.append("]");
        return builder.toString();
    }

}
