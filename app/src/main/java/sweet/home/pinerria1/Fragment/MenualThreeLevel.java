package sweet.home.pinerria1.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import sweet.home.pinerria1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenualThreeLevel extends Fragment {


    public MenualThreeLevel() {
        // Required empty public constructor
    }

//    public static final int FIRST_LEVEL_COUNT = 6;
//    public static final int SECOND_LEVEL_COUNT = 4;
//    public static final int THIRD_LEVEL_COUNT = 20;


    private ExpandableListView expandableListView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_menual_three_level, container, false);

        String[] parent = new String[]{"Welcome to families", "Enrollment"," Programs and curriculum"," Schedule","Nutrition","Parent Responsibilities"," Health and Safety"};

        String[] parent1 = new String[]{"Philosophy", "Vision", "Mission","Licensing, evaluation, and inspection","Confidentiality"};
        String[] parent2 = new String[]{"Registration", "Tuition and payment", "Waiting lists", "Required forms "," Termination policy","Accommodation for children"};
        String[] parent3 = new String[]{"About our curriculum", "Cotton Candy Class", "Lollipop Class", "Cupcake Class","Cookie Class","Ice-cream Class","Outdoor play","Winter","Summer","Ratios","Television"};
        String[] parent4 = new String[]{"Hours of operation", "Separation", "Nap and rest time", "Calendar","Teacher preparation days","Parent Training"};
        String[] parent5 = new String[]{"Kitchen", "Allergies"};
        String[] parent6 = new String[]{"Parent responsibilities", "Allergies"," Early arrivals","Late arrivals","Dismissal","Late pick up","Absences",
                                        "School pictures","Clothing","What to bring"," What to leave at home","Parent teacher conferences","Suggestions and complaints",
                                        "Ongoing parent communication","Birthdays","Special events","Visiting Home Sweet Home"};
        String[] parent7 = new String[]{"Health Policies", " Symptoms that indicate the need for exclusion","Emergency health care policy",
                                        "Medication administration"," Reporting suspected child abuse or neglect","Evacuation procedures",
                                            " School closings","Discipline and guidance"," Diaper changing","Toilet training"};


        String[] p1_1 = new String[]{"At Home Sweet Home, we believe that children are capable and resourceful learners and they have much to\n" +
                "contribute to their own learning and to ours as well.\n" +
                "Children need to be valued as individuals and we need to respect their right to express their thoughts and\n" +
                "feelings, regardless of their age or ability.\n" +
                "It is our responsibility, as early childhood professionals, to care for and educate our children and their families\n" +
                "through establishing secure, supportive and creative learning environments."};
        String[] p1_2 = new String[]{"Our vision is to be a model preschool in Riyadh, exemplifying high quality and international best practices in\n" +
                "early childhood care and education."};
        String[] p1_3 = new String[]{"Home Sweet Home Preschool will meet its vision through:\n" +
                "•\t empowering each individual to be an enthusiastic life-long learner and a confident global citizen of\n" +
                "the future.\n" +
                "•\t a curriculum focused on pedagogical integrity and research-based best practices.\n" +
                "•\t acknowledging and responding to each child’s potential.\n" +
                "•\t an authentic partnership with families and the community.\n" +
                "•\t continuous, on-going evaluation, improvement and quality assurance across the organization."};
        String[] p1_4 = new String[]{"Home Sweet Home Preschool is licensed by the Ministry of Education. Home Sweet Home also undergoes\n" +
                "an annual evaluation and assessment by Arabian Child, (Dubai, United Arab of Emirates) This third party\n" +
                "early childhood organization provides a thorough evaluation of all aspects of the school. This includes its\n" +
                "teaching approach, teacher qualifications, the curriculum, child assessment, health & safety, nutrition, the\n" +
                "environment, family & community relationships, leadership, and administration."};
        String[] p1_5 = new String[]{"All information pertaining to the admission about health or family issues are confidential. Information will\n" +
                "be shared with teachers and staff on a “need-to-know” basis. They have signed a written policy to protect\n" +
                "the confidentiality of medical and social information. Please respect the privacy of others in accordance with\n" +
                "our policy"};


        String[] p2_1 = new String[]{"In order to register your child, a downpayment of tuition fee along with the required forms must be submitted\n" +
                "and approved by Home Sweet Home Preschool Management. A slot cannot be reserved for your child\n" +
                "without a registration fee."};
        String[] p2_2 = new String[]{"Fees Amount Due time Status\n" +
                "Annual tuition\n" +
                "fee 42000 SR\n" +
                "8000 SR March\n" +
                "previous academic year\n" +
                "(reserving spot)\n" +
                "Non-refundable\n" +
                "13000 SR September\n" +
                " Refundable if the child only attends\n" +
                "one day and then withdraws.\n" +
                "10000 SR refundable if the child attends\n" +
                "2 days and then withdraws.\n" +
                "21000 SR November\n" +
                "8000 SR Non-refundable\n" +
                "13000 SR. Refundable if the child only\n" +
                "attends one day and then withdraws.\n" +
                "10000 SR refundable if the child attends\n" +
                "2 days and then withdraws."};
        String[] p2_3 = new String[]{"The waiting list for Home Sweet Home Preschool operates on a first come, first served basis. Siblings of\n" +
                "children currently enrolled in a program will be given priority for enrollment. When an opening occurs, the\n" +
                "first child on the waiting list of the appropriate age will be offered a slot. If the parent declines the slot, Home\n" +
                "Sweet Home Preschool Management will offer the slot to the next age-appropriate child on the waiting list\n" +
                "until that slot is filled. "};
        String[] p2_4 = new String[]{"•\t Registration form\n" +
                "•\t Copy of child’s birth certificate\n" +
                "•\t Copy of family card for Saudi nationals and copy of Iqama for non-Saudi residents\n" +
                "•\t Copy of ID for child’s nanny and driver\n" +
                "•\t Medical report\n" +
                "•\t Four pictures of the child\n" +
                "•\t Immunization record (Please note that it is the parent’s responsibility to keep the immunization record\n" +
                "up-to-date. If parents opt not to vaccinate, this must be discussed with school management)."};
        String[] p2_5 = new String[]{"Home Sweet Home Preschool may terminate enrollment under the following circumstances:\n" +
                "•\t Lack of adherence to tuition policies.\n" +
                "•\t Parent do not provide or falsify information on registration or in health records.\n" +
                "•\t Parents do not agree to follow and comply with the policies set forth in the handbook.\n" +
                "•\t Lack of parental cooperation with the school’s effort to resolve differences and/ or to meet the child’s\n" +
                "needs through parent/staff meetings or conferences.\n" +
                "•\t Inappropriate or abusive behavior or verbal threats by parents, relatives, guardians or other parties\n" +
                "towards staff. "};
        String[] p2_6 = new String[]{"If a child with a disability requires accommodation in order to participate in the program, parents must\n" +
                "discuss the steps and requirements for inclusion with Home Sweet Home Preschool Management. A plan of\n" +
                "action, acceptable to both parties, can then be drafted."};

        String[] p3_1 = new String[]{"We are very excited to present a globally-acclaimed and an award-winning curriculum, specializing in early\n" +
                "childhood education and care, namely the Creative Curriculum.\n" +
                "The program is designed to address the whole child including social, moral, cognitive, representational,\n" +
                "and physical development domains. A comprehensive, ongoing training supports implementation of the\n" +
                "curriculum and a participant-centered professional development program.\n" +
                "The Creative Curriculum provides opportunities for teachers and administrators to invent practical strategies\n" +
                "that support children’s ways of thinking and challenge them to construct new knowledge. Overall, our\n" +
                "program fosters children’s literacy and language development and helps children develop scientific and\n" +
                "mathematical knowledge. It uses art, construction, music, movement, and play to promote learning. Lessons\n" +
                "are planned with both the individual child and the group in mind. "};
        String[] p3_2 = new String[]{" Children in the Cotton Candy room need a lot of love and support as they start exploring the world.\n" +
                "Our activities for children of this age focus on building their self-esteem while creating opportunities for\n" +
                "interaction within groups to spark curiosity and socialization. We focus on cognitive and motor skills through\n" +
                "playtime and various activities. Development through age- appropriate materials and toys is promoted and\n" +
                "regular communication between parents and teachers seeks to keep you informed about your child’s day."};
        String[] p3_3 = new String[]{"Our children in the Lollipop room will continue to learn through sensory experiences which will enhance their\n" +
                "cognitive, language, motor and social skills. Here their ideas can be turned into words and phrases to express\n" +
                "themselves clearly and practice the value of sharing and cooperating through group play. Children at this stage\n" +
                "start to develop friendships and gain confidence as they use their words and thoughts to interact with others.\n" +
                "It is important for them to express themselves creatively while developing their unique skills and interests. "};
        String[] p3_4 = new String[]{"Our preschoolers in the Cupcake room are busy exploring the world around them and learning to\n" +
                "communicate their thoughts. Our caring teachers keep small hands busy and young minds engaged through\n" +
                "activities designed specifically for this age group. Your child will build skills and confidence through games,\n" +
                "songs, movement, art and creative expression. By encouraging child-directed play, we ensure that your child\n" +
                "develops at his or her own pace. Sharing, cooperating, and taking turns teaches your child the importance\n" +
                "of being a team member. "};
        String[] p3_5 = new String[]{"The Cookie room program provides a positive climate for developing good listening skills and increasing\n" +
                "social skills. Various activities and the learning area are used to stimulate a constant learning environment. We\n" +
                "create a shared framework and use languages to enable a four-year-old child learn and grow. We set realistic\n" +
                "expectations for this age group through reading & writing readiness skills, science, mathematical concepts,\n" +
                "and communication skills. Our qualified teachers create safe, appropriate, and stimulating environments\n" +
                "in physical, social and emotional development areas, in language and communication, in cognitive and\n" +
                "general knowledge as well as motor skills."};
        String[] p3_6 = new String[]{"The Kindergarten class helps set the foundation for the future. Students develop the language, reading,\n" +
                "writing, mathematical, and social skills that will help them achieve success throughout their academic and\n" +
                "daily lives. All of this learning occurs in a fun and challenging, centers-based classroom with the guidance of\n" +
                "caring and supportive teachers.\n" +
                "• The school reserves the right to transfer a child to a different class according to his/her individual\n" +
                "needs."};
        String[] p3_7 = new String[]{"The school provides several playgrounds areas: a shaded sand and water area, a special area for riding bikes.\n" +
                "Outdoor play is an important part of our daily curriculum despite any prevailing weather conditions. It is\n" +
                "important for parents to provide the appropriate clothing and gear for the weather conditions. "};
        String[] p3_8 = new String[]{"Our staff members wait until later in the day and will spend less time outside when temperatures are low. All\n" +
                "children will be monitored closely. Please make sure your child is dressed appropriately for outdoor play. "};
        String[] p3_9 = new String[]{"Children will participate in the shady and rubberized areas of the playground during the warm summer\n" +
                "days. Children are encouraged to drink water plentifully to replenish body fluids. Parents are encouraged to\n" +
                "provide sunscreen. "};
        String[] p3_10 = new String[]{"The school is required to follow the minimum staff-to-child ratios, dictated by the National Association for\n" +
                "the Education of Young Children (NAEYC).\n" +
                "Classroom Number of teaching staff (teachers and assistants) Maximum group size\n" +
                "Cotton Candy 1: 5 16\n" +
                "Lollipop 1: 6 20\n" +
                "Cupcake 1: 9 20\n" +
                "Cookie 1:10 20\n" +
                "Ice-cream 1:10 20"};
        String[] p3_11= new String[]{"At Home Sweet Home we believe that children learn through play. Every interaction they enjoy with their\n" +
                "classmates, friends and teachers provides them with experiences on which they build and grow. Children\n" +
                "are never required to sit and watch TV and TV is not offered in place of free play or learning activities. We aim\n" +
                "to provide active learning experiences for the children on a daily basis to fill their day without the need for\n" +
                "television time.\n" +
                "Relevant Internet clips applicable to the topic may be used as part of a lesson."};


        String[] p4_1= new String[]{"Home Sweet Home Preschool is open from 8:00 am to 1:30 pm Sunday to Thursday. The child should be\n" +
                "signed in and out each day"};
        String[] p4_2= new String[]{"During the first few days, a parent may plan to spend time with the child at the preschool. We recommend\n" +
                "that parents visit Home Sweet Home Preschool several times with their child after enrollment. A comfort\n" +
                "toy or other object from home often helps to make the transition to school easier and less traumatic.\n" +
                "Parents should tell the child when they are leaving and that they will return. The child should see the parent\n" +
                "interacting with the teachers. Parents should never leave the child without a good-bye. Once the parent\n" +
                "leaves, the teacher will then help the child feel comfortable and adjust to the new environment. "};
        String[] p4_3= new String[]{"All children will have the opportunity to rest in the morning hours, depending on their age group. Infants\n" +
                "and toddlers are aided in relaxing using a variety of methods: darkened room, soft music or story tapes,\n" +
                "rubbing backs, etc. We will try our best to accommodate parent’s requests in waking a sleeping child after\n" +
                "thirty minutes to one hour of sleep, but only when it is in the best interests of the child. ‘Non-nappers’ will\n" +
                "be encouraged to rest for half an hour. After that time, they may get up and participate in quiet activities.\n" +
                "Please supply a crib sheet, a soft blanket, and a small pillow (if needed) or soft toy. All items must fit in the\n" +
                "child’s cubby. Sleeping items will be sent home to be washed on the last day of the week."};
        String[] p4_4= new String[]{"you will receive an academic calendar from Home Sweet Home, indicating holidays, mother meetings, father\n" +
                "meetings and other important dates."};
        String[] p4_5= new String[]{"Teacher training at Home Sweet Home is a vital part of our ongoing commitment to excellence in order\n" +
                "to ensure that your child receives a high-quality learning experience. They undergo regular training and\n" +
                "professional development. This training often will take place during school holidays and before the start of\n" +
                "a new academic year. However, sometimes we may need to conduct training sessions for teachers during\n" +
                "a school day and then will require you to pick up your child early or keep him or her at home for a day or\n" +
                "two."};
        String[] p4_6= new String[]{"At Home Sweet Home Preschool, we hold training sessions for parents to help you build parenting skills and\n" +
                "complement your child’s learning at home. Training sessions are held by world-renowned early childhood\n" +
                "experts who are invited to our school in order to provide you with specialized training sessions.\n" +
                "Attending is optional but highly recommended"};

        String[] p5_1= new String[]{"Each day, we serve a nutritional, well-balanced meal for breakfast and lunch as well as a tasty snack.\n" +
                "Notice of these weekly menus for your child will be available every Thursday. "};
        String[] p5_2= new String[]{"It is imperative that you inform your child’s teacher as well as the office of any allergies (including food and\n" +
                "environmental allergies) that your child may have. Allergy lists are posted in each classroom to inform any\n" +
                "staff member working in that particular classroom. "};


        String[] p6_1= new String[]{"The following are the parents’ responsibilities upon enrolling their child and while he/she is attending the\n" +
                "school:\n" +
                "• Completing all forms for enrollment and registration.\n" +
                "• Keeping emergency telephone numbers up-to-date.\n" +
                "• Timely payment of fees in accordance with Home Sweet Home schedule.\n" +
                "• Updating immunization records.\n" +
                "• Picking up your child punctually at the end of each day\n" +
                "• Informing school of illness, vacations, other absences and late arrivals.\n" +
                "• Picking up your child immediately if contacted by the school due to illness or injury.\n" +
                "• Being considerate of the health of others in keeping your child home or making alternative care\n" +
                "arrangements when your child is not completely well.\n" +
                "• Notifying the school of the names of people authorized to pick up your child.\n" +
                "• Communicating any changes at home that may affect your child.\n" +
                "• Attending parent/teacher conferences and meetings.\n" +
                "• Providing the school with a change of clothes and diapers as necessary.\n" +
                "• Keeping your child’s fingernails trimmed and clean. "};
        String[] p6_2= new String[]{"It is mandatory that your child is signed in at the reception desk daily. Your child will be provided with an\n" +
                "electronic photo ID card to be scanned each morning. "};
        String[] p6_3= new String[]{"Some parents have special circumstances that require them to bring their child to school earlier. Our school\n" +
                "will be open at 7:15am. "};
        String[] p6_4= new String[]{"The daily routine begins promptly at 8:00 am. In order to take advantage of every learning experience, please\n" +
                "ensure that your child arrives on time. In the event of a change in this routine, please drop him/ her off with\n" +
                "the receptionist, who will then determine where your child should go, according to the daily schedule.\n" +
                "For safety reasons, the teacher must be able to give her undivided attention to the children in her classroom.\n" +
                "Therefore, she will be unable to discuss any issues or address concerns after class begins. In addition, late\n" +
                "arrivals are distracting to other students and disturb the learning process. Teachers will be more than happy\n" +
                "to discuss your concerns or answer any questions you may have during office hours.\n" +
                "Please note that any missed meals will not be made up if your child arrives after the designated breakfast,\n" +
                "lunch, or snack times."};
        String[] p6_5= new String[]{"Children are dismissed ONLY to parents, adult siblings, nannies, drivers, guardians, or others listed on the\n" +
                "child’s application as authorized for pick-up. Persons who come to pick up your child must be listed on\n" +
                "the application, know your password, and produce picture identification. Photocopies of the authorized\n" +
                "individual will be placed in the child’s file. Please notify the office and the child’s teacher in writing if you\n" +
                "intend on someone other than yourself picking up your child. Children will not be released to ANYONE not\n" +
                "listed on the application or if written consent is not given. Children will not be released to ANYONE under\n" +
                "the age of 18"};
        String[] p6_6= new String[]{"Preschool ends promptly at 1:30 pm. If you have not picked up your child at the designated time, we will\n" +
                "contact you. If you, as the parent, are unavailable, the emergency contact number will be contacted. If none\n" +
                "of the above is available, the child will go home with a staff member. Please contact Home Sweet Home\n" +
                "Preschool Management for more information. "};
        String[] p6_7= new String[]{"Advance notice of planned absences is greatly appreciated.\n" +
                "If your child will not be attending the school on a given day, either because of illness or just to stay at\n" +
                "home, please notify the school before 9:00 am. This helps the school plan for daily activities and appropriate\n" +
                "staffing. If your child is sick, due to an infestation or away on a family vacation, parents are still responsible for\n" +
                "payment of the full tuition fees."};
        String[] p6_8= new String[]{"School pictures are taken occasionally throughout the academic year and will be posted on Home Sweet\n" +
                "Home Preschool’s Social media channels. Amongst registration, the school assumes permission from parents\n" +
                "to post their child’s photos and videos.\n" +
                "All special events will be photographed throughout the year. A DVD of these events will be available at the\n" +
                "end of the year. The cost will be 300 SR."};
        String[] p6_9= new String[]{"Shoes\n" +
                "Children should wear sturdy shoes that allow for safety in running and playing. Shoes with heels or\n" +
                "flip flops are not permitted.\n" +
                "Clothes\n" +
                "Children should wear the school uniform that is weather-appropriate. If a girl wears a dress, shorts\n" +
                "must be worn underneath. A complete change of clothes should be kept in the cubby at all times. It\n" +
                "is the parents’ responsibility to replenish these extra clothes. Please ensure all items are labeled with\n" +
                "the child’s name.\n"+
                "School Uniform\n" +
                "School uniforms are available. Wearing school uniform identifies your child as a member of our\n" +
                "school and shows school spirit. You may purchase them in the office."};
        String[] p6_10= new String[]{"Your child should come to school each day dressed to play. All items of clothing should be clearly labeled.\n" +
                "Please provide the following items for your child while at Home Sweet Home:\n" +
                "1. seasonal change of clothes in a Ziploc bag.\n" +
                "2. safe footwear for children at all times. Open-toe sandals, flip flops, and fancy princess shoes are\n" +
                "discouraged.\n" +
                "3. diapers in unopened packages. Teachers will inform you when your child’s supply is low.\n" +
                "4. extra underwear!\n" +
                "5. a comfort toy for resting and making transition to school easier (please, one toy per child).\n" +
                "6. nap/ rest time supplies: crib sheet, blanket, and a small pillow.\n" +
                "7. on a particular days, children are permitted to bring an item, which relates to the theme, for “Show\n" +
                "and Tell”.\n" +
                "• a detailed supply list will be sent home with your child."};
        String[] p6_11= new String[]{"We ask you to leave candy, money, gum, jewelry, and toys (other than a soft toy to have for rest time) at\n" +
                "home."};
        String[] p6_12= new String[]{"Good communication is of the utmost importance. When a new family is accepted into our school, we share\n" +
                "openly about any concerns or questions that may be relevant for the welfare of the child. We welcome\n" +
                "questions, feedback, or discussions of any kind that are oriented towards a positive outcome for your child\n" +
                "or our children in general. Sensitive issues will be discussed in private at a mutually beneficial time."};
        String[] p6_13= new String[]{"High quality early childhood care and education is based on building positive relationships with families. In\n" +
                "order to provide and maintain these positive relationships with families, open communication between our\n" +
                "staff and parents is essential. Parent should always feel free to talk with their children’s teachers if they have\n" +
                "any questions or concerns about their child or the school. Please make an appointment to discuss these\n" +
                "issues with the person concerned. Questions about the policies and procedures should be directed to the\n" +
                "school management."};
        String[] p6_14= new String[]{"We are proud to announce that HSH is going Green. The majority of correspondence from the administration\n" +
                "and your child’s teachers will be done electronically through the school’s email system or HSH website\n" +
                "parent’s accounts. In addition, you will occasionally receive correspondence from your child’s teacher\n" +
                "throughout the year. The purpose of this is to notify you of classroom events, activities, special events, and\n" +
                "needed supplies. Please take the time to read all emails and correspondence sent home. These letters will be\n" +
                "distributed in your child’s folder or posted in the Home Sweet Home mail"};
        String[] p6_15= new String[]{"Unfortunately, we will be unable to accommodate a party with all the accessories that accompany a child’s\n" +
                "party. Should you be planning a party outside the school, please do not use the school as a means of\n" +
                "distributing invitations unless the entire class is invited. Children who do not get invited feel left out and\n" +
                "hurt. A class list is available in the office."};
        String[] p6_16= new String[]{"Several special events will be planned throughout the school year, such as a visit from community services.\n" +
                "You will be notified, in advance, of the time and any special requirements for any event.\n" +
                "In some events, children will be dismissed earlier, but parents will be notified."};
        String[] p6_17= new String[]{"Home Sweet Home Preschool maintains an “Open Door Policy”. It means parents are always welcome\n" +
                "to call or drop-in to see their child at any time. Please remember when visiting, children may react in an\n" +
                "excited manner that does not normally occur when the children are interacting with the teacher. Please be\n" +
                "considerate of disrupting the class during circle time and other group activities. Please note that each teacher\n" +
                "is required to supervise her group of children at all times within specific ratio requirements, therefore the\n" +
                "teacher may not be able to leave and attend to your questions. You can, however, watch through observation\n" +
                "TV screens or ask school management how you can participate in the classroom. "};

        String[] p7_1= new String[]{"When a child or staff member is known to have a communicable illness as outlined below, the child or staff\n" +
                "member will be excluded from attendance at Home Sweet Home for such a time. Any child who becomes\n" +
                "ill while at the school is sent home as soon as possible. Parents will be contacted immediately. If we are\n" +
                "unsuccessful in reaching parents, we will begin contacting alternate adults as listed in the emergency\n" +
                "contact information. For this reason, it is imperative that emergency contact information is kept up-to-date\n" +
                "and current. "};
        String[] p7_2= new String[]{"The following symptoms and illnesses indicate the need for a child or staff member to be excluded from the\n" +
                "program temporarily.\n" +
                "1. Fever and behavior-change or one or more of the symptoms listed below.\n" +
                "2. Vomiting within the last 24 hours\n" +
                "3. Diarrhea within the last 24 hours\n" +
                "4. Persistent cough and/ or sore, reddened throat.\n" +
                "5. Red, weeping eyes, and or discolored nasal drainage.\n" +
                "6. Skin eruptions or rash.\n" +
                "7. Extreme fatigue and/or malaise.\n" +
                "8. Head lice or nits.\n" +
                "Your child must be free of symptoms for at least 24 hours without the aid of medication before coming back\n" +
                "to school. "};
        String[] p7_3= new String[]{"Emergencies: Non-medical\n" +
                "In case of unusual weather such as heavy rain, floods & sand storms, children will be supervised at the school\n" +
                "until a parent or emergency contact person picks up the child. Fire and emergency drills are held throughout\n" +
                "the year and the children are familiarized with all emergency procedures. First aid and emergency supplies\n" +
                "are stored in each class in case of emergency. In the event of the school needing to be evacuated, the school\n" +
                "has made arrangements to keep the children safe. Accurate emergency information must be on file for each\n" +
                "child from the first day of school. Should an emergency occur, the school will use this information to contact\n" +
                "the parents and/or emergency-contacts.\n" +
                "16\n" +
                "Emergencies: Medical\n" +
                "In the event a child has a medical emergency, the school will notify the parents. If the parents are unavailable,\n" +
                "the individuals listed on the emergency list will be contacted. In the event that no one can be contacted,\n" +
                "decisions about the child (medical and transportation needs) will be made by the closest hospital’s doctors\n" +
                "or nurses"};
        String[] p7_4= new String[]{"We need a written order or prescription from your child’s physician before we can administer prescription\n" +
                "medicine to your child. The medication must be delivered to the school in its original container, labeled with\n" +
                "an expiration date. The dosage, name of medication, name of prescribing physician and current date must\n" +
                "also be included.\n" +
                "Non-prescription medication\n" +
                "Parents must sign a medicine-release form in order for staff to administer any of the following medication\n" +
                "to the child.\n" +
                "• Antihistamine\n" +
                "• Non-aspirin fever reducers / pain relievers\n" +
                "• Decongestants\n" +
                "• Anti-itching ointments\n" +
                "• Sunscreen\n" +
                "• Cough drops "};
        String[] p7_5= new String[]{"At Home Sweet Home we are mandated to report if we feel a child is being abused or neglected. Always be\n" +
                "sure to let your Director know when you drop your child off if he/she has any unexplained cuts or bruises. All\n" +
                "children who come to school with injuries have these logged into their file."};
        String[] p7_6= new String[]{"We are required by state law to do one fire drill per term at Home Sweet Home. We vary the time of day to\n" +
                "help the staff and children prepare to evacuate the building quickly and safely."};
        String[] p7_7= new String[]{"In the event of extreme weather conditions, the school is primarily concerned for the welfare of the children\n" +
                "and our staff. In the event of unscheduled closings due to extreme weather or other conditions, HSH will\n" +
                "follow the lead of the Saudi Schools. Parents will be informed of these closings by SMS."};
        String[] p7_8= new String[]{"Home Sweet Home uses the following information for the guidance and the discipline of our children.\n" +
                "Throughout a child’s development, he or she will inevitably behave in ways that are not acceptable,\n" +
                "either because it is dangerous, hurtful, or otherwise socially unacceptable. In order to help our children\n" +
                "develop appropriate social behaviors, we guide them by the most positive means available towards\n" +
                "good behavior. The following constitutes our formal disciplinary policy:\n" +
                "Redirection is always first:\n" +
                "By keeping the children involved in activities that they are interested in, we can often prevent situations\n" +
                "from arising that require further intervention. Also, by ensuring that the children are adequately\n" +
                "supervised, situations can be dissipated before they escalate. We can teach the children to resolve\n" +
                "their own disputes by taking advantage of opportunities that arise in which we can guide them to a\n" +
                "peaceful solution. If a baby is trying to take a toy away from an older child, we will ask the older child to\n" +
                "get a different toy for the baby. This helps show the child that he is entitled to hold on to the toy that\n" +
                "he was playing with, but that he can also help the other child to accept this. We will distract the child’s\n" +
                "attention from the undesired behavior and direct attention to something else.\n" +
                "An alternative disciplinary measure:\n" +
                "We will make use of time-out only as a means of assisting an angry or distressed child to ‘take a breather’\n" +
                "in a tense situation. Often this is all that is needed for a child to calm down. Our teachers will explain\n" +
                "why he/she is sitting out of an activity for a short time. She will stay with him/her to talk about the\n" +
                "incident and suggest a better way of resolving the problem. By doing this, the child is learning a better\n" +
                "way to resolve conflict. At no time will the child feel humiliated or ridiculed.\n" +
                "Positive Reinforcement:\n" +
                "It’s better to congratulate children for doing something well (in order to encourage them to repeat the\n" +
                "behavior) than to wait for them to do something wrong and punish them. Focus on positive things,\n" +
                "rather than on negative behaviors. "};
        String[] p7_9= new String[]{"It is the parent’s responsibility to provide diapers, wipes, and diaper cream for your child. The diaper changing\n" +
                "tables are cleaned and disinfected between each diaper change, and hand washing of the childcare providers\n" +
                "and each child is performed after each diaper change."};
        String[] p7_10= new String[]{"When you feel your child is ready for toilet teaching, we ask that you begin this teaching at home during a\n" +
                "weekend or vacation. We will follow through and encourage your child while in our care. The child must be\n" +
                "showing signs of readiness. When a child is ready, the process should go pretty quickly. The child must be kept in\n" +
                "pull-ups or 5-ply training pants at all times. Putting a child in diapers part time, and training pants part time can\n" +
                "be confusing and delay the training process. Please keep in mind that the activity level here can distract your\n" +
                "child from responding to an urge to use the potty, more so than at your home. Therefore, we may continue to use\n" +
                "diapers or pull-ups until your child can and will announce that he/she must use the bathroom (not just at home,\n" +
                "but here as well) and can control his/her bladder and bowels for a few minutes beyond that announcement.\n" +
                "Parents need to supply training pants with plastic pants or pull-ups, plus a couple of extra changes of clothing\n" +
                "each day. Don’t forget the socks!\n" +
                "During toilet training, we ask that the child be dressed in “user friendly” clothing, as much as possible. The\n" +
                "best items are shorts and pants with elastic waists, or dresses. Try to avoid really tight clothing, pants with\n" +
                "snaps and zippers, and overalls. These are difficult for children to remove “in a hurry”.\n" +
                "The school’s toilet learning procedures follow the recommendations of the American Academy of Pediatrics,\n" +
                "and work with you to make sure that toilet learning is carried out in a way that is appropriate for your child’s\n" +
                "physical and emotional abilities.\n" +
                "Your child may be ready for toilet learning if he or she:\n" +
                "• Can sense that the bowels are full.\n" +
                "• Can let you know when he/she has “to go”.\n" +
                "• Understands what is expected.\n" +
                "• Cooperates with your requests."};


        LinkedHashMap<String, String[]> thirdLevelParent1 = new LinkedHashMap<>();
        LinkedHashMap<String, String[]> thirdLevelParent2 = new LinkedHashMap<>();
        LinkedHashMap<String, String[]> thirdLevelParent3 = new LinkedHashMap<>();
        LinkedHashMap<String, String[]> thirdLevelParent4 = new LinkedHashMap<>();
        LinkedHashMap<String, String[]> thirdLevelParent5 = new LinkedHashMap<>();
        LinkedHashMap<String, String[]> thirdLevelParent6 = new LinkedHashMap<>();
        LinkedHashMap<String, String[]> thirdLevelParent7 = new LinkedHashMap<>();

        List<String[]> secondLevel = new ArrayList<>();
        List<LinkedHashMap<String, String[]>> data = new ArrayList<>();


        secondLevel.add(parent1);
        secondLevel.add(parent2);
        secondLevel.add(parent3);
        secondLevel.add(parent4);
        secondLevel.add(parent5);
        secondLevel.add(parent6);
        secondLevel.add(parent7);
        // secondLevel.add(serials);

        // movies category all data
        thirdLevelParent1.put(parent1[0], p1_1);
        thirdLevelParent1.put(parent1[1], p1_2);
        thirdLevelParent1.put(parent1[2], p1_3);
        thirdLevelParent1.put(parent1[3], p1_4);
        thirdLevelParent1.put(parent1[4], p1_5);

        // games category all data
        thirdLevelParent2.put(parent2[0], p2_1);
        thirdLevelParent2.put(parent2[1], p2_2);
        thirdLevelParent2.put(parent2[2], p2_3);
        thirdLevelParent2.put(parent2[3], p2_4);
        thirdLevelParent2.put(parent2[4], p2_5);
        thirdLevelParent2.put(parent2[5], p2_6);



        thirdLevelParent3.put(parent3[0], p3_1);
        thirdLevelParent3.put(parent3[1], p3_2);
        thirdLevelParent3.put(parent3[2], p3_3);
        thirdLevelParent3.put(parent3[3], p3_4);
        thirdLevelParent3.put(parent3[4], p3_5);
        thirdLevelParent3.put(parent3[5], p3_6);
        thirdLevelParent3.put(parent3[6], p3_7);
        thirdLevelParent3.put(parent3[7], p3_8);
        thirdLevelParent3.put(parent3[8], p3_9);
        thirdLevelParent3.put(parent3[9], p3_10);
        thirdLevelParent3.put(parent3[10], p3_11);


        thirdLevelParent4.put(parent4[0], p4_1);
        thirdLevelParent4.put(parent4[1], p4_2);
        thirdLevelParent4.put(parent4[2], p4_3);
        thirdLevelParent4.put(parent4[3], p4_4);
        thirdLevelParent4.put(parent4[4], p4_5);
        thirdLevelParent4.put(parent4[5], p4_6);

        thirdLevelParent5.put(parent5[0], p5_1);
        thirdLevelParent5.put(parent5[1], p5_2);

        thirdLevelParent6.put(parent6[0], p6_1);
        thirdLevelParent6.put(parent6[1], p6_2);
        thirdLevelParent6.put(parent6[2], p6_3);
        thirdLevelParent6.put(parent6[3], p6_4);
        thirdLevelParent6.put(parent6[4], p6_5);
        thirdLevelParent6.put(parent6[5], p6_6);
        thirdLevelParent6.put(parent6[6], p6_7);
        thirdLevelParent6.put(parent6[7], p6_8);
        thirdLevelParent6.put(parent6[8], p6_9);
        thirdLevelParent6.put(parent6[9], p6_10);
        thirdLevelParent6.put(parent6[10], p6_11);
        thirdLevelParent6.put(parent6[11], p6_12);
        thirdLevelParent6.put(parent6[12], p6_13);
        thirdLevelParent6.put(parent6[13], p6_14);
        thirdLevelParent6.put(parent6[14], p6_15);
        thirdLevelParent6.put(parent6[15], p6_16);
        thirdLevelParent6.put(parent6[16], p6_17);

        thirdLevelParent7.put(parent7[0], p7_1);
        thirdLevelParent7.put(parent7[1], p7_2);
        thirdLevelParent7.put(parent7[2], p7_3);
        thirdLevelParent7.put(parent7[3], p7_4);
        thirdLevelParent7.put(parent7[4], p7_5);
        thirdLevelParent7.put(parent7[5], p7_6);
        thirdLevelParent7.put(parent7[6], p7_7);
        thirdLevelParent7.put(parent7[7], p7_8);
        thirdLevelParent7.put(parent7[8], p7_9);
        thirdLevelParent7.put(parent7[9], p7_10);


        // all data
        data.add(thirdLevelParent1);
        data.add(thirdLevelParent2);
        data.add(thirdLevelParent3);
        data.add(thirdLevelParent4);
        data.add(thirdLevelParent5);
        data.add(thirdLevelParent6);
        data.add(thirdLevelParent7);


        expandableListView = (ExpandableListView) view.findViewById(R.id.mainList);
        ThreeLevelListAdapter threeLevelListAdapterAdapter = new ThreeLevelListAdapter(getActivity(), parent, secondLevel, data);

        expandableListView.setAdapter( threeLevelListAdapterAdapter );



        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    expandableListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });



        return view;
    }



    public class ThreeLevelListAdapter extends BaseExpandableListAdapter {

        String[] parentHeaders;
        List<String[]> secondLevel;
        private Context context;
        List<LinkedHashMap<String, String[]>> data;

        public ThreeLevelListAdapter(Context context, String[] parentHeader, List<String[]> secondLevel, List<LinkedHashMap<String, String[]>> data) {
            this.context = context;

            this.parentHeaders = parentHeader;

            this.secondLevel = secondLevel;

            this.data = data;
        }

        @Override
        public int getGroupCount() {
            return parentHeaders.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {


            // no idea why this code is working

            return 1;

        }

        @Override
        public Object getGroup(int groupPosition) {

            return groupPosition;
        }

        @Override
        public Object getChild(int group, int child) {


            return child;


        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group2, null);
            TextView text = (TextView) convertView.findViewById(R.id.lblListHeader);
            text.setText(this.parentHeaders[groupPosition]);

            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

            final SecondLevelExpandableListView secondLevelELV = new SecondLevelExpandableListView(context);

            String[] headers = secondLevel.get(groupPosition);


            List<String[]> childData = new ArrayList<>();
            HashMap<String, String[]> secondLevelData=data.get(groupPosition);

            for(String key : secondLevelData.keySet())
            {


                childData.add(secondLevelData.get(key));

            }



            secondLevelELV.setAdapter(new SecondLevelAdapter(context, headers,childData));

            secondLevelELV.setGroupIndicator(null);


            secondLevelELV.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                int previousGroup = -1;

                @Override
                public void onGroupExpand(int groupPosition) {
                    if(groupPosition != previousGroup)
                        secondLevelELV.collapseGroup(previousGroup);
                    previousGroup = groupPosition;
                }
            });


            return secondLevelELV;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    public class SecondLevelExpandableListView extends ExpandableListView
    {

        public SecondLevelExpandableListView(Context context) {
            super(context);
        }

        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            //999999 is a size in pixels. ExpandableListView requires a maximum height in order to do measurement calculations.
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(999999, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }


    public class SecondLevelAdapter extends BaseExpandableListAdapter {

        private Context context;


        List<String[]> data;

        String[] headers;


        public SecondLevelAdapter(Context context, String[] headers, List<String[]> data) {
            this.context = context;
            this.data = data;
            this.headers = headers;
        }

        @Override
        public Object getGroup(int groupPosition) {

            return headers[groupPosition];
        }

        @Override
        public int getGroupCount() {

            return headers.length;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {


            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item2, null);
            TextView text = (TextView) convertView.findViewById(R.id.lblListItem);
            String groupText = getGroup(groupPosition).toString();
            text.setText(groupText);

            return convertView;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {

            String[] childData;

            childData = data.get(groupPosition);


            return childData[childPosition];
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_third, null);

            TextView textView = (TextView) convertView.findViewById(R.id.lblListItem3);

            String[] childArray=data.get(groupPosition);

            String text = childArray[childPosition];

            textView.setText(text);

            return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            String[] children = data.get(groupPosition);


            return children.length;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

}



